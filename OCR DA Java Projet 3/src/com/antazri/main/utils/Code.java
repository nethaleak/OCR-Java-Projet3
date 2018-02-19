package com.antazri.main.utils;

public abstract interface Code<T> {

	public abstract void addElement(T element);
	public abstract void generateCode();
	public abstract void resetCode();

}