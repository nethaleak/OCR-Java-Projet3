package com.antazri.main.utils;

public interface Combination<T> {
	
	public T[] generateProposition();
	public String correction(T[] answer);
	public boolean compare(T[] answer);

}
