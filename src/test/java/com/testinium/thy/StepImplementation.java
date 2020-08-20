package com.testinium.thy;

import com.thoughtworks.gauge.Step;

public class StepImplementation extends BaseTest {

    @Step("Thy Uygulamasını Aç")
    public void uygulamaAc() {
        logger.info("Uygulama başlatılıyor...");
        //Acılan popup lar için eklendi...
        //appDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        //appDriver.findElement(By.id("com.turkishairlines.mobile:id/frPrivacy_btnAccept")).click();
    }

    @Step("Bilet Al Butonu tıkla")
    public void biletAl() {
        clickById("com.turkishairlines.mobile:id/acMain_btnBooking");
        logger.info("Bilet al butonuna tıklandı...");
    }

    @Step("Tek yon sec")
    public void tekYonSec() {
        clickById("com.turkishairlines.mobile:id/frDashboard_tvOneWay");
        logger.info("Tek yön seçildi...");
    }

    @Step("<from> Nereden <to> nereye gidilecek")
    public void ucusYonleriniSec(String from, String to) {
        clickById("com.turkishairlines.mobile:id/frDashboard_llFrom");
        setById("com.turkishairlines.mobile:id/frAirportSelection_etSearch", from);
        logger.info("Kalkış yönü Sabiha Gökçen olarak seçildi...");
        clickById("com.turkishairlines.mobile:id/itemAirport_tvBottom");
        clickById("com.turkishairlines.mobile:id/frDashboard_llTo");
        setById("com.turkishairlines.mobile:id/frAirportSelection_etSearch", to);
        logger.info("Varış yönü Esenboğa olarak seçildi...");
        clickById("com.turkishairlines.mobile:id/itemAirport_tvBottom");
    }

    @Step("Ucus tarihi belirle")
    public void tarihBelirle() {
        clickById("com.turkishairlines.mobile:id/frDashboard_rlDeparture");
        swipeDown(725,"com.turkishairlines.mobile:id/frDashboard_calendarPickerView");
        clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.FrameLayout[7]/android.widget.TextView");
        clickById("com.turkishairlines.mobile:id/frDashboard_btnDone");
        logger.info("Swipe methodu kullanılarak rastgele bir ucuç tarihi seçildi...");
    }


    //Hes Kodu alıp senaryonun sonuna kadar gidebilmek için yolcu sayısını arttırmadım...
   /* @Step("Yolcu sayısı arttır")
    public void yolcuArttır(){
        //clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.FrameLayout[7]/android.widget.TextView");
    }*/

    @Step("Uygun ucus ara")
    public void ucusAra() {
        clickById("com.turkishairlines.mobile:id/frDashboard_btnSearch");
        logger.info("Uçuş aranıyor...");
    }

    @Step("Ucus Sec")
    public void ucusSec() {
        swipeDown(800,"com.turkishairlines.mobile:id/frFlightSearch_rvFlight");
        logger.info("Swipe methodu kullanılara listelenen uçuşlardan rastgele bir tanesi seçildi...");
        optionClick("android.view.View",5);
        clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup");
        clickById("com.turkishairlines.mobile:id/frFlightSearch_btnContinue");
    }

    @Step("<name> ad <surname> soyad <gun> gun <ay> ay <yil> yil <email> email <tcno> tc yolcu bilgileri Gir")
    public void yolcuBilgileri(String name, String surname, String gun,String ay,String yil, String email, String tcno) {
        elementSize = optionLength("android.widget.RelativeLayout");
        if (elementSize > 2) {
            //Daha önce yolcu bilgisi girilmişse bu buton aktiflenmeli ilk yolcuysa
            clickById("com.turkishairlines.mobile:id/frPickPassengerlistitemadd_tvShortName");
            yolcuBilgileriGir(name, surname,gun,ay,yil, email, tcno);
        } else {
            //clickById("com.turkishairlines.mobile:id/frPickPassengerlistitemadd_tvShortName");
            yolcuBilgileriGir(name, surname,gun,ay,yil,email, tcno);
        }
    }

    public void yolcuBilgileriGir(String name, String surname, String gun,String ay,String yil, String email, String tcno) {
        setById("com.turkishairlines.mobile:id/frAddNewPassenger_etAddName", name);
        logger.info("Yolcu adı girildi...");
        setById("com.turkishairlines.mobile:id/frAddNewPassenger_etAddLastName", surname);
        logger.info("Yolcu soyadı girildi...");
        optionClick("android.widget.CheckBox",0);
        logger.info("Yolcu cinsiyet girildi...");
        clickById("com.turkishairlines.mobile:id/frAddNewPassenger_etAddDateOfBirth");
        setByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.EditText", gun);
        logger.info("Yolcu doğum tarihi gün bilgisi girildi...");
        setByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.EditText", ay);
        logger.info("Yolcu doğum tarihi ay bilgisi girildi...");
        setByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[3]/android.widget.EditText", yil);
        logger.info("Yolcu doğum tarihi yıl bilgisi girildi...");
        clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[3]/android.widget.EditText");
        clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.EditText");
        clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[2]/android.widget.EditText");
        setByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[1]/android.widget.EditText", ay);
        clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.NumberPicker[3]/android.widget.EditText");
        clickById("com.turkishairlines.mobile:id/datePickerBottom_ivDone");
        logger.info("Yolcu doğum tarihi girildi...");
        setById("com.turkishairlines.mobile:id/frAddNewPassenger_etAddEmail", email);
        logger.info("Yolcu e-mail girildi...");
        clickByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.CheckBox[1]");
        logger.info("Yolcu uyruk girildi...");
        setById("com.turkishairlines.mobile:id/frAddNewPassenger_etAddTCKN", tcno);
        logger.info("Yolcu TC-NO girildi...");
        clickById("com.turkishairlines.mobile:id/frAddNewPassenger_btnAddPassenger");
        logger.info("Yolcu bilgileri kaydedildi...");
    }

    @Step("<hescode> Hes Kodu <ulkekodu> ulke kodu ve <telefon> telefon bilgileri Gir")
    public void yolcuTelBilgileri(String hesKodu, String ulkeKodu, String tel) {
        clickById("com.turkishairlines.mobile:id/frPickPassenger_btnContinue");
        setById("com.turkishairlines.mobile:id/itemPassengerHes_etHesCode", hesKodu);
        logger.info("Yolcu Hes-Code girildi...");
        clickById("com.turkishairlines.mobile:id/frPassengerHesCode_btnContinue");
        setById("com.turkishairlines.mobile:id/frAddContactInfo_etCountryCode", ulkeKodu);
        setById("com.turkishairlines.mobile:id/frAddContactInfo_etPhoneNumber", tel);
        logger.info("Yolcu Telefon girildi...");
        clickById("com.turkishairlines.mobile:id/frAddContactInfo_btnContinue");
    }

    /*//Bir numarayla bir defa kayıt olundugu için bir daha gelmıyor buradaki ekranlar
    @Step("Miles Kayıt")
    public void milesKayıt() {
        clickById("com.turkishairlines.mobile:id/frAddContactInfo_cbBlueCheckMilesSmiles");
        logger.info("Miles üyeliği seçildi...");
        setById("com.turkishairlines.mobile:id/frAddContactInfo_etPassword","Testinium.123");
        logger.info("Miles üyeliği için şifre girildi...");
        setById("com.turkishairlines.mobile:id/frAddContactInfo_etCheckPassword","Testinium.123");
        logger.info("Miles üyeliği için şifre kontrolü yapıldı...");
        clickById("com.turkishairlines.mobile:id/frAddContactInfo_btnContinue");
        clickById("com.turkishairlines.mobile:id/dgSignupSuccess_btnSignIn");
        clickById("com.turkishairlines.mobile:id/frAddContactInfo_btnContinue");
        clickById("com.turkishairlines.mobile:id/layoutGenericBottom_btnContinue");
    }*/

    @Step("Koltuk secimi ilerle")
    public void koltukSecIlerle() {
        clickById("com.turkishairlines.mobile:id/layoutGenericBottom_btnContinue");
        logger.info("İşlem devam ediyor...");
    }

    @Step("Ucus Ozet Devam Et")
    public void ucusOzetDevam() {
        clickById("com.turkishairlines.mobile:id/layoutGenericBottom_btnContinue");
    }

    @Step("<kartno> kartNo,<karttarih> kart tarih ve <cvc> cvc bilgileri gir")
    public void kartBilgileriGir(String kartNo, String kartTarih, String csv) {
        //Kredi Kartı seçimi
        optionClick("android.view.ViewGroup", 2);
        logger.info("Kredi Kartıyla ödeme seçildi...");
        setById("com.turkishairlines.mobile:id/frNewCreditCard_etAddCardNumber", kartNo);
        logger.info("Kart numarası girildi...");
        setById("com.turkishairlines.mobile:id/frNewCreditCard_etExpiryDate", kartTarih);
        logger.info("Tarih bilgisi girildi...");
        setById("com.turkishairlines.mobile:id/frNewCreditCard_etAddCvc", csv);
        logger.info("Kart CVC bilgisi girildi...");
        waitBySeconds(2);
        swipeDown(700,"com.turkishairlines.mobile:id/frNewCreditCard_nsvRoot");
    }

    @Step("Islem iptal")
    public void islemIptal() {
        clickById("com.turkishairlines.mobile:id/toolbarBase_tvCancel");
        logger.info("Tüm İşlemler iptal edilecek...");
        clickById("com.turkishairlines.mobile:id/dgBase_btnPositive");
        logger.info("Tüm işlemler iptal edildi...");
        logger.info("!!!Otomasyon tamamlandı!!!");
    }
}