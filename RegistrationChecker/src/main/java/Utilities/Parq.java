//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.Utilities;

import com.google.common.base.Strings;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;


public class Parq {

    private static WebDriver activeDriver;
    private static Map<String, WebDriver> windows = new HashMap();
    private static Map<String, WebDriver> tabs = new HashMap();
    private static WebElement activeWebElement;
    private static WebElement storedWebElement;
    private static List<WebElement> webElements = new ArrayList();
    private static Select htmlSelectElement = null;
    private static Map<String, WebElement> storedwebElements = new HashMap();
    private static Locator lastCollectBy;
    private static Locator lastFocusBy;
    private static int focusTimeout;
    private static int pageLoadTimeout;
    private static int probeTimeout;
    private static int expectTimeout;
    private static int collectTimeout;
    private static int collectAttempts;
    private static String currentElementText = "";
    private static Boolean execute = true;
    private static Long ELEMENT_POLL_FREQENCY;
    private static int ELEMENT_POLL_LIMIT;
    private static int ELEMENT_PROBE_LIMIT;

    public Parq() {
    }


    public static Parq focus(Locator locator) throws ParqException {
        if (execute) {
            lastFocusBy = locator;
            int count = 0;
            Logger.logger(new String[]{locator.getType().name(), locator.getValue()});

            while(true) {
                List elementList = null;

                try {
                    elementList = activeDriver.findElements(locator.getBy());
                    Dimension dim;
                    if (elementList.size() == 1) {
                        dim = ((WebElement)elementList.get(0)).getSize();
                        if (dim.width == 0 && dim.height == 0) {
                            Logger.logWarn("Focus : One element found with zero dimensions");
                        } else {
                            Logger.logger(new String[]{"Focused on element with dimensions [ " + dim.height + " , " + dim.width + " ]"});
                        }

                        activeWebElement = (WebElement)elementList.get(0);
                        currentElementText = activeWebElement.getText();
                    } else {
                        if (elementList.size() <= 1) {
                            if (count++ >= 30) {
                                throw new ParqException("Focus : Element not on DOM after 10 location attempts");
                            }

                            Logger.logWarn("Focus : Element not on DOM.  Retry [ " + count + " ] ...");
                            pause(1);
                            continue;
                        }

                        Logger.logWarn("More than one element matches : found " + elementList.size() + ".  Selecting first element visible in viewport");
                        Iterator var4 = elementList.iterator();

                        while(true) {
                            if (!var4.hasNext()) {
                                Logger.logWarn("Focus : No matching element is visible in viewport. Selecting first element matching location criterion" + Colours.RESET);
                                activeWebElement = (WebElement)elementList.get(0);
                                break;
                            }

                            WebElement element = (WebElement)var4.next();
                            dim = element.getSize();
                            if (element.isDisplayed()) {
                                activeWebElement = element;
                                currentElementText = activeWebElement.getText();
                                Logger.logger(new String[]{"Focused on element with dimensions [ " + dim.height + " , " + dim.width + " ]"});
                                break;
                            }
                        }
                    }
                } catch (StaleElementReferenceException var6) {
                    if (count++ < 30) {
                        Logger.logWarn(Colours.YELLOW_BACKGROUND_BRIGHT + "Focus : Stale Element : retry [ " + count + " ] ...");
                        pause(1);
                        continue;
                    }

                    throw new ParqException("Focus : Element search error :  Element stale after 10 location attempts");
                }

                if (activeWebElement.getTagName().equals("select")) {
                    Logger.logger(new String[]{"Html Select Element detected by focus."});
                    htmlSelectElement = new Select(activeWebElement);
                } else {
                    htmlSelectElement = null;
                }
                break;
            }
        }

        return new Parq();
    }


    public static Parq touch() {
        if (execute) {
            Logger.logger(new String[]{"Touching", "Current Element"});
            int count = 0;

            while (true) {
                try {
                    if (activeWebElement.getTagName().equals("option")) {
                        activeWebElement.click();
                    } else {
                        Actions builder = new Actions(getDriver());
                        builder.moveToElement(activeWebElement).click(activeWebElement);
                        builder.perform();
                    }

                    Logger.logger(new String[]{"Touched "});
                    break;
                } catch (ElementClickInterceptedException | JavascriptException | ElementNotVisibleException var2) {
                    ((JavascriptExecutor) activeDriver).executeScript("arguments[0].scrollIntoView(true);", new Object[]{activeWebElement});
                    if (count++ >= 20) {
                        //("Focus : Element access error :  Element not accessible after attempts");
                    }

                    Logger.logWarn("Element not clickable : Move into view : retry [ " + count + " ]");
                    pause(1);
                } catch (WebDriverException var3) {
                    Logger.logger(new String[]{Colours.RED_BRIGHT + "Touch : Error : " + var3.getMessage() + Colours.RESET});
                    //("Focus : Element access error : " + var3.getMessage());
                }
            }
        }

        return new Parq();
    }

    public static Parq refresh() {
        if (execute) {
        }

        return new Parq();
    }

    public static Parq hover() {
        if (execute) {
            Actions action = new Actions(activeDriver);
            action.moveToElement(activeWebElement).perform();
        }

        return new Parq();
    }




    public static Parq compose(String text) {
        if (execute) {
            Logger.logger(new String[]{"", text});
            activeWebElement.sendKeys(new CharSequence[]{text});
            Logger.logger(new String[]{"Composed"});
        }

        return new Parq();
    }

    public static Parq compose(Keys keyToSend) {
        if (execute) {
            Logger.logger(new String[]{keyToSend.name()});
            activeWebElement.click();
            activeWebElement.sendKeys(new CharSequence[]{keyToSend});
            Logger.logger(new String[]{"Composed"});
        }

        return new Parq();
    }




    public static Parq go(String url) throws ParqException {
        if (execute) {
            Logger.logger(new String[]{"Navigate to", url});

            try {
                activeDriver.get(url);
                Logger.logger(new String[]{"Navigated"});
            } catch (WebDriverException var2) {
                throw new ParqException("Navigate : " + var2.getMessage());
            }
        }

        return new Parq();
    }


    public static Parq contains() throws Exception {
        return contains(Parq.AssertStrategy.HARD.getAssertAction());
    }

    public static Parq contains(Parq.AssertStrategy assertStrategy) throws Exception {
        return contains(assertStrategy.getAssertAction());
    }

    private static Parq contains(boolean throwException) throws Exception {
        if (execute) {
            Logger.logger(new String[]{"Contains : Check for content ", "Any"});
            if (activeWebElement.getText().isEmpty()) {
                if (throwException) {
                    throw new Exception("Contains : Current Element contains no text");
                }

                Logger.logWarn("Contains : Current Element contains no text");
            } else {
                Logger.logger(new String[]{Colours.GREEN_BRIGHT_BACKGROUND + "Matched" + Colours.RESET});
            }
        }

        return new Parq();
    }

    public static Parq matches(String content) throws Exception {
        return matches(Parq.AssertStrategy.HARD.getAssertAction(), content);
    }

    public static Parq matches(Parq.AssertStrategy assertStrategy, String content) throws Exception {
        return matches(assertStrategy.getAssertAction(), content);
    }

    private static Parq matches(boolean throwException, String content) throws Exception {
        if (execute) {
            Logger.logger(new String[]{Colours.TAB + "Matches : " + Colours.CYAN_BACKGROUND_BRIGHT + content + Colours.RESET});
            String elementText = activeWebElement.getText();
            if (!activeWebElement.getText().equals(content)) {
                if (throwException) {
                    throw new Exception("The content did not match. Expected-[ " + content + " ], Actual-[ " + elementText + " ]");
                }

                Logger.logWarn("The content did not match. Expected-[ " + content + " ], Actual-[ " + elementText + " ]");
            } else {
                Logger.logger(new String[]{Colours.GREEN_BRIGHT_BACKGROUND + "Matched" + Colours.RESET});
            }
        }

        return new Parq();
    }

    public static Parq contains(String content) throws Exception {
        return contains(Parq.AssertStrategy.HARD.getAssertAction(), content);
    }

    public static Parq contains(Parq.AssertStrategy assertStrategy, String content) throws Exception {
        return contains(assertStrategy.getAssertAction(), content);
    }

    private static Parq contains(boolean throwException, String content) throws Exception {
        if (execute) {
            Logger.logger(new String[]{"Text", content});
            String elementText = activeWebElement.getText();
            if (!activeWebElement.getText().contains(content)) {
                if (throwException) {
                    throw new Exception("The content did not contain expected text. Expected [ " + content + " ], Actual [ " + activeWebElement.getText() + " ]");
                }

                Logger.logWarn("The content did not contain expected text. Expected [ " + content + " ], Actual [ " + activeWebElement.getText() + " ]");
            } else {
                Logger.logger(new String[]{"Contained"});
            }
        }

        return new Parq();
    }

    public static Parq contains(Html.Tag tag) throws Exception {
        return contains(Parq.AssertStrategy.HARD.getAssertAction(), tag);
    }

    public static Parq contains(Parq.AssertStrategy assertStrategy, Html.Tag tag) throws Exception {
        return contains(assertStrategy.getAssertAction(), tag);
    }

    private static Parq contains(boolean throwException, Html.Tag tag) throws Exception {
        if (execute) {
            Logger.logger(new String[]{"Contains Tag", tag.getValue()});
            String elementTag = activeWebElement.getTagName();
            if (!elementTag.equals(tag.getValue())) {
                if (throwException) {
                    throw new Exception("The element is not of expected tag type . Expected [ " + tag.getValue() + " ], Actual [ " + elementTag + " ]");
                }

                Logger.logWarn("The element is not of expected tag type . Expected [ " + tag.getValue() + " ], Actual [ " + elementTag + " ]");
            } else {
                Logger.logger(new String[]{"Contained tag"});
            }
        }

        return new Parq();
    }


    public static Locator tag(Html.Tag value) {
        return new Locator(By.xpath(".//" + value.getValue()), value.getValue(), main.java.Utilities.Locator.Type.TAG);
    }

    public static Locator tag(String value) {
        return new Locator(By.xpath(".//" + value), value, main.java.Utilities.Locator.Type.TAG);
    }

    public static Locator tag(Html.Tag tagName, String value) {
        return new Locator(By.xpath(".//" + tagName.getValue() + "[(text()='" + value + "' )]"), value, main.java.Utilities.Locator.Type.TAG);
    }

    public static Locator tagContains(Html.Tag tagName, String value) {
        return new Locator(By.xpath(".//" + tagName.getValue() + "[contains(text(),'" + value + "')]"), value, main.java.Utilities.Locator.Type.TAG);
    }

    public static Locator tag(Html.Tag tagName, Integer index) {
        return new Locator(By.xpath(".//" + tagName.getValue() + "[" + index + "]"), index.toString(), main.java.Utilities.Locator.Type.TAG);
    }


    public static Parq placeholder(String placeholder) throws Exception {
        return placeholder(Parq.AssertStrategy.HARD.getAssertAction(), placeholder);
    }

    public static Parq placeholder(Parq.AssertStrategy assertStrategy, String placeholder) throws Exception {
        return placeholder(assertStrategy.getAssertAction(), placeholder);
    }

    private static Parq placeholder(boolean throwException, String placeholder) throws Exception {
        if (execute) {
            Logger.logger(new String[]{"Locating placeholder ", placeholder});
            String actualPlaceholder = activeWebElement.getAttribute("placeholder");
            if (!actualPlaceholder.equals(placeholder)) {
                if (throwException) {
                    throw new Exception("The element contains unexpected placeholder. Expected [ " + placeholder + " ], Actual [ " + actualPlaceholder + " ]");
                }

                Logger.logWarn("The element contains unexpected placeholder. Expected [ " + placeholder + " ], Actual [ " + actualPlaceholder + " ]");
            } else {
                Logger.logger(new String[]{"Placeholder found"});
            }
        }

        return new Parq();
    }

    public static Parq value(String value) throws Exception {
        return value(Parq.AssertStrategy.HARD.getAssertAction(), value);
    }

    public static Parq value(Parq.AssertStrategy assertStrategy, String value) throws Exception {
        return value(assertStrategy.getAssertAction(), value);
    }

    private static Parq value(boolean throwException, String value) throws Exception {
        if (execute) {
            Logger.logger(new String[]{"Locating value", value});
            String actualValue = activeWebElement.getAttribute("value");
            if (!actualValue.equals(value)) {
                if (throwException) {
                    throw new Exception("The element contains unexpected value. Expected [ " + value + " ], Actual [ " + actualValue + " ]");
                }

                Logger.logWarn("The element contains unexpected value. Expected [ " + value + " ], Actual [ " + actualValue + " ]");
            } else {
                Logger.logger(new String[]{"Value found"});
            }
        }

        return new Parq();
    }


    private static Parq attribute(boolean throwException, String value) throws Exception {
        if (execute) {
            Logger.logger(new String[]{"Locating attribute", value});
            String actualValue = activeWebElement.getAttribute(value);
            if (Strings.isNullOrEmpty(actualValue)) {
                if (throwException) {
                    throw new Exception("The element does not contain expected attribute. Expected [ " + value + " ], Actual [ " + actualValue + " ]");
                }

                Logger.logWarn("The element does not contain expected attribute. Expected [ " + value + " ], Actual [ " + actualValue + " ]");
            } else {
                Logger.logger(new String[]{"Attribute found"});
            }
        }

        return new Parq();
    }

    public static Parq attribute(String attribute, String value) throws Exception {
        return attribute(Parq.AssertStrategy.HARD.getAssertAction(), attribute, value);
    }

    public static Parq attribute(Parq.AssertStrategy assertStrategy, String attribute, String value) throws Exception {
        return attribute(assertStrategy.getAssertAction(), attribute, value);
    }

    private static Parq attribute(boolean throwException, String attribute, String value) throws Exception {
        if (execute) {
            Logger.logger(new String[]{"Locating attribute / value pair ", attribute + " : " + value});
            String actualAttribute = activeWebElement.getAttribute(attribute);
            if (Strings.isNullOrEmpty(actualAttribute)) {
                if (throwException) {
                    throw new Exception("The element does not contain expected attribute. Expected [ " + attribute + " ], Actual [ None ]");
                }

                Logger.logWarn("The element does not contain expected attribute. Expected [ " + attribute + " ], Actual [ None ]");
            }

            if (!actualAttribute.equals(value)) {
                if (throwException) {
                    throw new Exception("The element does not contain expected attribute value. Expected [ " + value + " ], Actual [ " + actualAttribute + " ]");
                }

                Logger.logWarn("The element does not contain expected attribute. Expected [ " + value + " ], Actual [ " + actualAttribute + " ]");
            } else {
                Logger.logger(new String[]{"Attribute / Value pair found"});
            }
        }

        return new Parq();
    }


    public static Parq end() {
        execute = true;
        return new Parq();
    }

    public static Parq stash() {
        if (execute) {
            storedWebElement = activeWebElement;
        }

        return new Parq();
    }

    public static Parq stash(String key) {
        if (execute) {
            storedwebElements.put(key, activeWebElement);
        }

        return new Parq();
    }

    public static Parq unstash() {
        if (execute) {
            activeWebElement = storedWebElement;
        }

        return new Parq();
    }

    public static Parq unstash(String key) {
        if (execute) {
            if (storedwebElements.containsKey(key)) {
                activeWebElement = (WebElement) storedwebElements.get(key);
            } else {
                System.out.println("unstash key not found: " + key);
            }
        }

        return new Parq();
    }

    public static Parq hoard() {
        if (execute) {
        }

        return new Parq();
    }

    public static Parq hoard(String key) {
        if (execute) {
        }

        return new Parq();
    }

    public static Parq unhoard() {
        if (execute) {
        }

        return new Parq();
    }

    public static Parq unhoard(String key) {
        if (execute) {
        }

        return new Parq();
    }

    public static Parq clear() {
        if (execute) {
            Logger.logger(new String[]{Colours.TAB + "Clearing", "Current Element"});
            activeWebElement.clear();
        }

        Logger.logger(new String[]{"Cleared"});
        return new Parq();
    }

    public static Parq pause(Integer seconds) {
        Logger.logger(new String[]{"Seconds ", seconds.toString()});
        pause((long) (seconds * 1000));
        Logger.logger(new String[]{"Resume "});
        return new Parq();
    }

    public static Parq pause(Long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

        return new Parq();
    }

    public static Parq capture() {
        if (execute) {
        }

        return new Parq();
    }


    public static void window(String windowName) throws Exception {
        Logger.logger(new String[]{"Opening ", "Tab : " + windowName});
        if (Strings.isNullOrEmpty(windowName)) {
            throw new Exception("tab : Invalid parameter");
        } else {
            if (windows.containsKey(windowName)) {
                Iterator var1 = windows.entrySet().iterator();

                while (var1.hasNext()) {
                    Entry me = (Entry) var1.next();
                    if (me.getKey().equals(windowName)) {
                        activeDriver = (WebDriver) me.getValue();
                        ((JavascriptExecutor) activeDriver).executeScript("alert('Window : " + windowName + "')", new Object[0]);
                        pause(3);
                        activeDriver.switchTo().alert().accept();
                        break;
                    }
                }
            } else {
                windows.put(windowName, Driver.initChromeDriver("CHROME_DARK"));
                window(windowName);
            }

        }
    }


    public static Parq show() {
        Logger.logger(new String[]{"Attributes", "Current Element"});
        System.out.println(Colours.MAGENTA_BACKGROUND_BRIGHT + "*** Current Focus ***" + Colours.RESET);
        Dimension dim = activeWebElement.getSize();
        System.out.println("Tag : " + activeWebElement.getTagName());
        System.out.println("id : " + activeWebElement.getAttribute("id"));
        System.out.println("name : " + activeWebElement.getAttribute("name"));
        System.out.println("value : " + activeWebElement.getAttribute("value"));
        System.out.println("Class : " + activeWebElement.getAttribute("class"));
        System.out.println("Dimension X : " + dim.width);
        System.out.println("Dimension Y : " + dim.height);
        System.out.println("Coordinate X : " + activeWebElement.getLocation().x);
        System.out.println("Coordinate Y : " + activeWebElement.getLocation().y);
        System.out.println("Enabled : " + activeWebElement.isEnabled());
        System.out.println("Displayed : " + activeWebElement.isDisplayed());
        System.out.println("Selected : " + activeWebElement.isSelected());
        System.out.println("Placeholder : " + activeWebElement.getAttribute("placeholder"));
        System.out.println("Text : " + activeWebElement.getText());
        return new Parq();
    }

    public static Parq ascend() {
        if (execute) {
            Logger.logger(new String[]{Colours.TAB + "Ascend to", "Parent"});
            activeWebElement = activeWebElement.findElement(By.xpath(".."));
            Logger.logger(new String[]{"Ascended"});
        }

        currentElementText = activeWebElement.getText();
        return new Parq();
    }

    public static Parq ascend(Locator locator) throws ParqException {
        if (execute) {
            Logger.logger(new String[]{Colours.TAB + "Ascend : With Locator : " + locator.getType().name() + " : " + Colours.CYAN_BACKGROUND_BRIGHT + locator.getValue() + Colours.RESET});
            if (locator.getType() != main.java.Utilities.Locator.Type.TAG) {
                throw new ParqException("Ascend(By) currently takes only TAG as a Type");
            }

            String path = "ancestor::" + locator.getValue() + "[position()=1]";
            activeWebElement = activeWebElement.findElement(By.xpath(path));
            Logger.logger(new String[]{Colours.GREEN_BRIGHT_BACKGROUND + "Ascended" + Colours.RESET});
        }

        currentElementText = activeWebElement.getText();
        return new Parq();
    }

    public static Parq descend() {
        if (execute) {
            Logger.logger(new String[]{"Descend", "First child"});
            activeWebElement = activeWebElement.findElement(By.xpath(".//*[1]"));
            Logger.logger(new String[]{"Descended"});
        }

        currentElementText = activeWebElement.getText();
        return new Parq();
    }

    public static Parq descend(Locator locator) throws ParqException {
        if (execute) {
            Logger.logger(new String[]{locator.getType().name(), locator.getValue()});

            try {
                activeWebElement = activeWebElement.findElement(locator.getBy());
                Logger.logger(new String[]{"Descended"});
            } catch (NoSuchElementException var2) {
                Logger.logger(new String[]{Colours.RED_BRIGHT + "Descend : Element not on DOM " + Colours.RESET});
                throw new ParqException("Descend : Element not on DOM");
            } catch (Exception var3) {
                throw new ParqException(var3.getMessage());
            }
        }

        currentElementText = activeWebElement.getText();
        return new Parq();
    }

    public static Parq traverse() throws ParqException {
        if (execute) {
            Logger.logger(new String[]{Colours.TAB + "Locate : ", "following sibling" + Colours.RESET});

            try {
                activeWebElement = activeWebElement.findElement(By.xpath("following-sibling::*[1]"));
                Logger.logger(new String[]{"Traversed"});
            } catch (Exception var1) {
                Logger.logger(new String[]{Colours.RED_BRIGHT + "Traverse : Sibling not found" + Colours.RESET});
                throw new ParqException("Traverse :  Sibling not found");
            }
        }

        currentElementText = activeWebElement.getText();
        return new Parq();
    }


    public static void setDriver(WebDriver webDriver) {
        activeDriver = webDriver;
        windows.put("Home", activeDriver);
        tabs.put("Home", activeDriver);
        //activeDriver.manage().timeouts().pageLoadTimeout((long) pageLoadTimeout, TimeUnit.SECONDS);
        setImplicitWait(focusTimeout);
       // activeDriver.manage().timeouts().implicitlyWait((long) focusTimeout, TimeUnit.SECONDS);
    }


    private static Properties readProperties() {
        InputStream inputStream = null;
        Properties properties = new Properties();
        String propFileName = "config.properties";

        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(propFileName);
            if (inputStream == null) {
                throw new FileNotFoundException("PARQ property file '" + propFileName + "' not found");
            }

            properties.load(inputStream);
            inputStream.close();
        } catch (Exception var4) {
            Logger.logWarn(var4.getMessage());
        }

        return properties;
    }

    private static void setImplicitWait(int seconds) {
        //activeDriver.manage().timeouts().implicitlyWait((long) seconds, TimeUnit.SECONDS);
    }


    public static WebElement getWebElement() {
        return activeWebElement;
    }

    public static WebDriver getDriver() {
        return activeDriver;
    }

    public static void setCurrentElementText(String text) {
        currentElementText = text;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getTagName() {
        return activeWebElement.getTagName();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String getAttribute(String attribute) {
        return activeWebElement.getAttribute(attribute);
    }

    public static Locator css(String attribute, String value) {
        return new Locator(By.xpath(".//*[@" + attribute + "='" + value + "']"), value, main.java.Utilities.Locator.Type.CSS);
    }

    public static Locator className(String className) {
        return new Locator(By.xpath(".//*[contains(concat(' ', normalize-space(@class), ' '), ' " + className + " ')]"), className, main.java.Utilities.Locator.Type.CLASS);
    }

    public static Locator classNames(String classNames) {
        return new Locator(By.xpath(".//*[@class='" + classNames + "']"), classNames, main.java.Utilities.Locator.Type.CLASSES);
    }

    public static Locator id(String id) {
        return new Locator(By.xpath(".//*[@id='" + id + "']"), id, main.java.Utilities.Locator.Type.ID);
    }

    public static Locator name(String name) {
        return new Locator(By.xpath(".//*[@name='" + name + "']"), name, main.java.Utilities.Locator.Type.NAME);
    }

    public static Locator text(String value) {
        return new Locator(By.xpath(".//*[(text()='" + value + "' )]"), value, main.java.Utilities.Locator.Type.TEXT);
    }


    public static enum AssertStrategy {
        SOFT(false),
        HARD(true);

        boolean action;

        private AssertStrategy(boolean value) {
            this.action = value;
        }

        public boolean getAssertAction() {
            return this.action;
        }
    }

    public static enum Direction {
        BACK,
        REFRESH,
        FORWARD;

        private Direction() {
        }
    }
}