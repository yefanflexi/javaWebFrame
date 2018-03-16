package com.flexi.javaWebFrame;

import com.flexi.javaWebFrame.utils.BeanHelper;
import com.flexi.javaWebFrame.utils.ClassHelper;
import com.flexi.javaWebFrame.utils.ClassUtil;
import com.flexi.javaWebFrame.utils.ControllerHelper;
import com.flexi.javaWebFrame.utils.IocHelper;

/**
 * @author vixtel
 *
 */
public final class HelperLoader {
	
	public static void init() {
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for (Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName(),true);
		}
		
	}

}
