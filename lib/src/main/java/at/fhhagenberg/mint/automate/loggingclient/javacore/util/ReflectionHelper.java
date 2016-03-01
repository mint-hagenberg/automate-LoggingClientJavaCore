package at.fhhagenberg.mint.automate.loggingclient.javacore.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * ReflectionToolkit has the ability to return class parameters during runtime
 * using the java.lang.reflection API
 *
 * @author Christoph Purrer
 */
public class ReflectionHelper {
	/**
	 * Checks if the provided Class or Interface is implementing or inheriting
	 * the specified interface.
	 *
	 * @param clazz                        the clazz
	 * @param interface2ImplementOrInherit the interface2 implement or inherit
	 * @return true, if is implementing or inheriting interface
	 */
	public static boolean isImplementingOrInheritingInterface(Class<?> clazz,
															  Class<?> interface2ImplementOrInherit) {
		// 1.) check the interfaces of this class
		for (Class<?> implementedInterface : clazz.getInterfaces()) {
			if (implementedInterface.getSimpleName().equalsIgnoreCase(
					interface2ImplementOrInherit.getSimpleName())) {
				// if (implementedInterface.getInterfaces().length == 0) {
				// logger.info("clazz SimpleName {}", clazz.getSimpleName());
				return true;
				// }
			} else if (isImplementingOrInheritingInterface(
					implementedInterface, interface2ImplementOrInherit)) {
				// logger.info("clazz SimpleName {}", clazz.getSimpleName());
				return true;
			}
		}

		if (clazz.getSuperclass() != null) {
			if (clazz
					.getSuperclass()
					.getSimpleName()
					.equalsIgnoreCase(
							interface2ImplementOrInherit.getSimpleName())) {
				// if (implementedInterface.getInterfaces().length == 0) {
				// logger.info("clazz SimpleName {}", clazz.getSimpleName());
				return true;
				// }
			} else if (isImplementingOrInheritingInterface(
					clazz.getSuperclass(), interface2ImplementOrInherit)) {
				// logger.info("clazz SimpleName {}", clazz.getSimpleName());
				return true;
			}
		}

		// 2.) recursively check the interfaces of the superclass(es)
		if (clazz.getSuperclass() != null) {
			return isImplementingOrInheritingInterface(clazz.getSuperclass(),
					interface2ImplementOrInherit);
		}

		return false;
	}

	/**
	 * checks if the provided Class or Interface is implementing or inheriting
	 * the specified interface.
	 *
	 * @param clazz                        the clazz
	 * @param interface2ImplementOrInherit the interface2 implement or inherit
	 * @return true, if is implementing or inheriting interface
	 */
	public static boolean isImplementingOrInheritingInterface(Class<?> clazz,
															  String interface2ImplementOrInherit) {
		// 1.) check the interfaces of this class
		for (Class<?> implementedInterface : clazz.getInterfaces()) {
			if (implementedInterface.getSimpleName().equalsIgnoreCase(
					interface2ImplementOrInherit)) {
				// if (implementedInterface.getInterfaces().length == 0) {
				// logger.info("clazz SimpleName {}", clazz.getSimpleName());
				return true;
				// }
			} else if (isImplementingOrInheritingInterface(
					implementedInterface, interface2ImplementOrInherit)) {
				// logger.info("clazz SimpleName {}", clazz.getSimpleName());
				return true;
			}
		}

		// 2.) recursively check the interfaces of the superclass(es)
		if (clazz.getSuperclass() != null) {
			return isImplementingOrInheritingInterface(clazz.getSuperclass(),
					interface2ImplementOrInherit);
		}

		return false;
	}

	/**
	 * Extends class.
	 *
	 * @param clazz          the clazz
	 * @param superClazzName the super clazz name
	 * @return true, if successful
	 */
	public static boolean isExtendingClass(Class<?> clazz, String superClazzName) {
		if (clazz != null) {
			// a.) check current class
			if (clazz.getName().equalsIgnoreCase(superClazzName)) {
				return false;
			}

			// b.) check super class
			Class<?> superClazz = clazz.getSuperclass();
			if (superClazz != null) {
				if (superClazz.getName().equalsIgnoreCase(superClazzName)) {
					return true;
				}
				if (superClazz.getName().equalsIgnoreCase("java.lang.Object") == false) {
					return isExtendingClass(superClazz, superClazzName);
				}
			}
		}
		return false;
	}

	/**
	 * Extends class.
	 *
	 * @param clazz      the clazz
	 * @param superClazz the super clazz name
	 * @return true, if successful
	 */
	public static boolean isExtendingClass(Class<?> clazz, Class<?> superClazz) {
		if (clazz != null) {
			// a.) check current class
			if (clazz.getName().equalsIgnoreCase(superClazz.getName())) {
				return false;
			}

			// b.) check super class
			Class<?> superClazzBase = clazz.getSuperclass();
			if (superClazzBase != null) {
				if (superClazzBase.getName().equalsIgnoreCase(
						superClazz.getName())) {
					return true;
				}
				if (superClazzBase.getName().equalsIgnoreCase(
						"java.lang.Object") == false) {
					return isExtendingClass(superClazzBase, superClazz);
				}
			}
		}
		return false;
	}

	/**
	 * Gets the methods.
	 *
	 * @param clazz the clazz
	 * @return the methods
	 */
	public static Map<String, Method> getMethods(Class<?> clazz) {
		Map<String, Method> methods = new HashMap<String, Method>();
		Method clazzMethods[] = clazz.getDeclaredMethods();
		for (int m = 0; m < clazzMethods.length; m++) {
			Method method = clazzMethods[m];
			methods.put(method.getName(), method);
		}
		// getDeclaredMethods() excludes inherited methods. getMethods() can be
		// used to at least get public inherited methods as well.
		clazzMethods = clazz.getMethods();
		for (int m = 0; m < clazzMethods.length; m++) {
			Method method = clazzMethods[m];
			methods.put(method.getName(), method);
		}
		return methods;
	}

	/**
	 * Invoke method.
	 *
	 * @param object     the object
	 * @param methodName the method name
	 * @param parameter  the parameter
	 * @return the object
	 * @throws InvocationTargetException -
	 * @throws IllegalAccessException    -
	 * @throws IllegalArgumentException  -
	 */
	public static Object invokeMethod(Object object, String methodName,
									  Object... parameter) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Object returnValue = null;
		if (object != null) {
			Map<String, Method> clazzMethods = getMethods(object.getClass());
			if (clazzMethods.containsKey(methodName)) {
				Method method = clazzMethods.get(methodName);

				return returnValue = method.invoke(object, parameter);
			}
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public static <T> T instantiateClass(Class<T> clazz, String path)
			throws IllegalAccessException, InstantiationException,
			ClassNotFoundException, ClassCastException {
		return (T) Class.forName(path).newInstance();
	}

	public static Class<?> invokeClass(String path)
			throws ClassNotFoundException {
		return Class.forName(path);
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> invokeClass(String path, Class<? extends T> ext)
			throws ClassNotFoundException {
		return (Class<? extends T>) Class.forName(path);
	}
}
