package cn.smartmvc.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 注解一值保留到运行期
@Target(ElementType.METHOD) // 此注解只能写在方法上
public @interface RequestMapping {
	String value();
}
