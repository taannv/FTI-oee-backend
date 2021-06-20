package com.dds.oee.common;

import java.util.function.Supplier;

/**
 * Author : duybv
 * Aug 24, 2019
 */

@FunctionalInterface
public interface ThrowingSupplier<T, E extends Exception> {

	T get() throws E;

	static <T,  E extends Exception> Supplier<T> getThrow(ThrowingSupplier<T, E> throwingSupplier) {
		return () -> {
			try {
				return throwingSupplier.get();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

}
