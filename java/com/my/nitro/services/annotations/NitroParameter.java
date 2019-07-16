package com.my.nitro.services.annotations;


import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */

@Retention(RUNTIME)
@Target({  PARAMETER, FIELD })
public @interface NitroParameter {
	String String = null;

	String value();

	String inputFormat() default "";

	Class returnType() default java.lang.String.class;

}
