package com.dds.oee.common;

import java.util.function.Consumer;

/**
 * Author : duybv
 * Aug 24, 2019
 */

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {

	void accept(T t) throws E;

	static <T,  E extends Exception> Consumer<T> acceptThrow(ThrowingConsumer<T, E> throwingConsumer) {
		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	static <T, E extends Exception> Consumer<T> acceptHandle(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception e) {
				try {
					E eCast = exceptionClass.cast(e);
					System.err.println("Cause occured: " + eCast.getMessage());
				} catch (ClassCastException ccEx) {
					throw new RuntimeException(e);
				}
			}
		};
	}

}
