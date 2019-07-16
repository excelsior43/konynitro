/**
 * 
 */
package com.my.nitro.services.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Yasir, sonu.yasir@gmail.com
 *
 */

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface NitroDecorator {

	Class value();

}
