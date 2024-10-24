package com.example.validationdemo;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerControler {
    // add an iitbinder... to convert
    //remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
@GetMapping("/")
    public String showFrom(Model theModel){
    theModel.addAttribute("customer",new Customer());
    return "customer-form";
}
@PostMapping("/processForm")
    public String processForm(
    @Valid @ModelAttribute("customer") Customer theCustomer,
    BindingResult theBindingResult){
    System.out.println("Last Name: |" + theCustomer.getLastName() + "|");

    System.out.println("Binding results: " + theBindingResult);

    System.out.println("\n\n");
    if (theBindingResult.hasErrors()){
        return "customer-form";
    }else {
        return "customer-conformation";
    }
}

}
//@InitBinder: Bu anotasyon, bir Spring MVC denetleyicisinde (controller) yer alan metodu işaretler. Bu metodun amacı,
// gelen HTTP isteklerinde yer alan form verilerinin bağlanması sırasında ek yapılandırmalar veya özelleştirmeler yapmaktır.
//WebDataBinder: Spring MVC'de form verilerinin model nesnelerine bağlanmasını sağlayan sınıftır. Bu bağlayıcı, gelen
// istekten (örneğin bir formdan) gelen verileri uygun model nesnesine atar.
//StringTrimmerEditor: Bu, gelen String verilerindeki gereksiz boşlukları (başındaki ve sonundaki) temizlemek için
//kullanılan bir düzenleyicidir. Bu düzenleyici, nullAsEmpty olarak ayarlandığında, boş stringler otomatik olarak
//null değerine çevrilir.
//
//dataBinder.registerCustomEditor: Bu metod, String türündeki verileri StringTrimmerEditor ile ilişkilendirir.
// Yani, formdan gelen her String alanı için, varsa baştaki ve sondaki boşluklar kırpılır. Eğer alan tamamen boşsa,
// null olarak işlenir.
