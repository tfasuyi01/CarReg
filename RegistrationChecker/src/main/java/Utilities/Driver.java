package main.java.Utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.http.W3CHttpCommandCodec;
import org.openqa.selenium.remote.http.W3CHttpResponseCodec;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    static WebDriver webDriver;
    static String projectPath = System.getProperty("user.dir");
    public static RemoteWebDriver createDriverFromSession(final SessionId sessionId, URL command_executor){

        CommandExecutor executor = new HttpCommandExecutor(command_executor) {

            @Override
            public Response execute(Command command) throws IOException {
                Response response = null;
                if (command.getName() == "newSession") {
                    response = new Response();
                    response.setSessionId(sessionId.toString());
                    response.setStatus(0);
                    response.setValue(Collections.<String, String>emptyMap());

                    try {
                        Field commandCodec = null;
                        commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
                        commandCodec.setAccessible(true);
                        commandCodec.set(this, new W3CHttpCommandCodec());
                        //commandCodec.set(this, new JsonHttpCommandCodec());

                        Field responseCodec = null;
                        responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
                        responseCodec.setAccessible(true);
                        responseCodec.set(this, new W3CHttpResponseCodec());
                        //commandCodec.set(this, new JsonHttpCommandCodec());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {
                    response = super.execute(command);
                }
                return response;
            }
        };

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, "true");
        capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS,  "true");
        capabilities.setCapability(CapabilityType.SUPPORTS_LOCATION_CONTEXT,  "true");
        capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION,  "true");
        return new RemoteWebDriver(executor, capabilities );
    }

    public static WebDriver buildDriver(String browserType) {

        switch(browserType)
	    {
	    	case "IE":
	    		webDriver = initInternetExplorerDriver();
	    		break;
	    	case "FIREFOX":
	    		webDriver = initFirefoxDriver();
	    		break;
	    	case "CHROME":
            webDriver = initChromeDriver("Normal");
            break;
            case "CHROME_DARK":
                webDriver = initChromeDriver( "Dark" );
                break;
	    	default:
	    		System.out.println("browser : "+ browserType + "is invalid, launching Chrome as browser of choice..");
	    		webDriver = initChromeDriver("Normal");
	    			
	    }
        
        HttpCommandExecutor executor =  (HttpCommandExecutor) ((RemoteWebDriver) webDriver ).getCommandExecutor();
        URL url = executor.getAddressOfRemoteServer();
        SessionId session_id = ((RemoteWebDriver) webDriver).getSessionId();
        //System.out.println("url:"+url);
        //System.out.println("session_id:"+session_id);
        System.out.println();
        System.out.println("\033[1;94m"+session_id+" "+url+"\033[0m");
        System.out.println();
        return webDriver;
    }
    
	static WebDriver initChromeDriver(String mode)  {

		String exePath = "src/main/resources/File/chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver" ,exePath);
		Map<String, Object> prefs = new HashMap<>();
		// Disable notification popup
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("plugins.plugins_disabled" , "Chrome PDF Viewer");
        prefs.put("profile.default_content_settings.popups", 0 );

		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE); //to handle Selenium Timed out receiving message from renderer
		options.addArguments("download.default_directory", "c:\\tmp");
        options.addArguments("download.prompt_for_download", "false");
        options.addArguments("plugins.always_open_pdf_externally", "false");
        options.addArguments("--disable-default-apps");
        if( mode.equals("Dark"))
            options.addArguments("--force-dark-mode");

        options.setExperimentalOption("prefs", prefs);
		webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();


		return webDriver;
	}
	
	private static WebDriver initFirefoxDriver()  {
		String ffExePath = projectPath + "\\drivers\\firefox\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver",ffExePath);
		webDriver = new FirefoxDriver();
		webDriver.manage().window().maximize();
		
		return webDriver;
	}
	
	private static WebDriver initInternetExplorerDriver()  {
		String ieExePath = projectPath + "\\drivers\\iedriver\\IEDriverServer.exe";
		System.setProperty("webdriver.gecko.driver",ieExePath);
		webDriver = new InternetExplorerDriver();
		webDriver.manage().window().maximize();

		return webDriver;
	}
    
}
