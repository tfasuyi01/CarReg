package main.java.test;


import main.java.EnvCong.BaseClass;
import main.java.Utilities.ExtractTextData;
import main.java.Utilities.Html;
import main.java.Utilities.Parq;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static main.java.Utilities.Parq.*;
import static main.java.Utilities.PropertiesManager.getProperty;


public class CarRegistration extends BaseClass {


    @Test
    public void checkCarRegistration() throws Exception {

        List<String> s = ExtractTextData.readFile();
        Random random = new Random(12345);
        int rand_int1 = random.nextInt(50000);
        String mileage = Integer.toString(rand_int1);

        for (int i = 0; i <= s.size(); i++) {
            switch (s.get(i).trim()) {
                case "AD58 VNF":
                    focus(name("vehicleReg"));
                    touch();
                    compose(s.get(i));
                    focus(name("Mileage"));
                    touch();
                    compose(mileage);
                    focus(tag(Html.Tag.SPAN, " Get my car valuation "));
                    touch();
                    ExtractTextData.compareOutputData(s.get(i).trim().replaceAll("\\s+", ""), VerifyRegNo.make(CarDataEnum.CAR1.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR1.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR1.YEAR.getValue()));
                    break;
                case "BW57 BOW":
                    go(getProperty("car.url"));
                    focus(name("vehicleReg"));
                    touch();
                    compose(s.get(i));
                    focus(name("Mileage"));
                    touch();
                    compose(mileage);
                    focus(tag(Html.Tag.SPAN, " Get my car valuation "));
                    touch();
                    Parq.pause(2);
                    List<WebElement> checkReg_1 = driver.findElements(By.xpath(".//*[text()=\" Sorry, we couldn't find your car\"]"));
                    if (checkReg_1.size() > 0) {
                        System.out.println(s.get(i) + " " + "Not foumd");
                        break;
                    }
                    ExtractTextData.compareOutputData(s.get(1).trim().replaceAll("\\s+", ""), VerifyRegNo.make(CarDataEnum.CAR3.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR3.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR3.YEAR.getValue()));
                    break;
                case "KT17DLX":
                    go(getProperty("car.url"));
                    focus(name("vehicleReg"));
                    touch();
                    compose(s.get(i));
                    focus(name("Mileage"));
                    touch();
                    compose(mileage);
                    focus(tag(Html.Tag.SPAN, " Get my car valuation "));
                    touch();
                    List<WebElement> checkReg = driver.findElements(By.xpath(".//*[text()=\" Sorry, we couldn't find your car\"]"));
                    if (checkReg.size() > 0) {
                        System.out.println(s.get(i) + " " + "Not foumd");
                        break;
                    }
                    ExtractTextData.compareOutputData(s.get(i).trim().replaceAll("\\s+", ""), VerifyRegNo.make(CarDataEnum.CAR4.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR4.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR4.YEAR.getValue()));
                    break;
                case "SG18 HTN":
                    go(getProperty("car.url"));
                    focus(name("vehicleReg"));
                    touch();
                    compose(s.get(i));
                    focus(name("Mileage"));
                    touch();
                    compose(mileage);
                    focus(tag(Html.Tag.SPAN, " Get my car valuation "));
                    touch();
                    Parq.pause(2);
                    List<WebElement> checkReg_2 = driver.findElements(By.xpath(".//*[text()=\" Sorry, we couldn't find your car\"]"));
                    if (checkReg_2.size() > 0) {
                        System.out.println(s.get(i) + " " + "Not foumd");
                        break;
                    }
                    ExtractTextData.compareOutputData(s.get(i).trim().replaceAll("\\s+", ""), VerifyRegNo.make(CarDataEnum.CAR2.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR2.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR2.YEAR.getValue()));

            }

        }

    }
}


//        if ("AD58 VNF".equals(s.get(0).trim())) {
//            focus(name("vehicleReg"));
//            touch();
//            compose(s.get(0));
//            focus(name("Mileage"));
//            touch();
//            compose(mileage);
//            focus(tag(Html.Tag.SPAN, " Get my car valuation "));
//            touch();
//            ExtractTextData.compareOutputData(s.get(0).trim().replaceAll("\\s+",""), VerifyRegNo.make(CarDataEnum.CAR1.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR1.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR1.YEAR.getValue()));
//        }
//        if ("BW57 BOW".equals(s.get(1).trim())) {
//            go(getProperty("car.url"));
//            focus(name("vehicleReg"));
//            touch();
//            compose(s.get(1));
//            focus(name("Mileage"));
//            touch();
//            compose(mileage);
//            focus(tag(Html.Tag.SPAN, " Get my car valuation "));
//            touch();
//            List<WebElement> menuHoverLinks = driver.findElements(By.xpath(".//*[text()=\" Sorry, we couldn't find your car\"]"));
//            if(menuHoverLinks.size() >0){
//                System.out.println(s.get(1)+ " " + "Not foumd");
//
//            }
//
//            ExtractTextData.compareOutputData(s.get(1).trim().replaceAll("\\s+",""), VerifyRegNo.make(CarDataEnum.CAR3.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR3.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR3.YEAR.getValue()));
//
//        }
//        if (s.get(2).trim().equals("KT17DLX")) {
//            go(getProperty("car.url"));
//            focus(name("vehicleReg"));
//            touch();
//            compose(s.get(1));
//            focus(name("Mileage"));
//            touch();
//            compose(mileage);
//            focus(tag(Html.Tag.SPAN, " Get my car valuation "));
//            touch();
//            ExtractTextData.compareOutputData(s.get(2).trim().replaceAll("\\s+",""), VerifyRegNo.make(CarDataEnum.CAR4.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR4.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR4.YEAR.getValue()));
//        }
//        if ("SG18 HTN".equals(s.get(3).trim())) {
//            Parq.go(getProperty("car.url"));
//            focus(name("vehicleReg"));
//            touch();
//            compose(s.get(1));
//            focus(name("Mileage"));
//            touch();
//            compose(mileage);
//            focus(tag(Html.Tag.SPAN, " Get my car valuation "));
//            touch();
//            List<WebElement> menuHoverLinks = driver.findElements(By.xpath(".//*[text()=\" Sorry, we couldn't find your car\"]"));
//            if(menuHoverLinks.size() >0){
//                Assert.fail(s.get(1)+ " " + "Not foumd");
//            }
//
//            ExtractTextData.compareOutputData(s.get(3).trim().replaceAll("\\s+",""), VerifyRegNo.make(CarDataEnum.CAR4.MAKE.getValue()), VerifyRegNo.make(CarDataEnum.CAR4.MODEL.getValue()), VerifyRegNo.make(CarDataEnum.CAR4.YEAR.getValue()));
//        }
//




