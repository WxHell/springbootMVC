package com.example.validationdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    //define default course code
    public String value() default "LUV";
    //define default error message
    public String message() default "must start with LUV";
    //define default groups
    public Class<?>[] groups() default {};
    //define default payloads
    public Class<? extends Payload>[] payload() default {};
}









//@Target, bu anotasyonun nerelerde kullanılabileceğini belirtir.
//ElementType.METHOD: Bu, anotasyonun metotların üzerine uygulanabileceğini gösterir.
//ElementType.FIELD: Bu, anotasyonun alanlara (field) uygulanabileceğini gösterir.
//@Retention, anotasyonun hangi yaşam döngüsünde tutulacağını belirtir.
//RetentionPolicy.RUNTIME: Bu, anotasyonun çalışma zamanında (runtime) kullanılacağını belirtir.
//@Constraint(validatedBy = CourseCodeConstraintValidator.class)Bu sayede, anotasyon çalışma zamanında kullanılabilir ve
// doğrulama işlemi sırasında aktif olur.
//Bu anotasyon, özel bir validasyon (doğrulama) mantığını tanımlar ve validasyon işlemini yapacak sınıfı belirtir.
//validatedBy kısmı, validasyonu gerçekleştirecek sınıfı işaret eder. Burada, CourseCodeConstraintValidator sınıfı,
//CourseCode anotasyonuyla işaretlenmiş alanların doğrulama mantığını içerir.