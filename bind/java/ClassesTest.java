// Copyright 2016 The Go Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package go;

import android.test.InstrumentationTestCase;
import android.test.MoreAsserts;

import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import go.javapkg.Javapkg;

public class ClassesTest extends InstrumentationTestCase {
	public void testConst() {
		assertEquals("const Float", Float.MIN_VALUE, Javapkg.floatMin());
		assertEquals("const String", java.util.jar.JarFile.MANIFEST_NAME, Javapkg.manifestName());
		assertEquals("const Int", 7, Integer.SIZE, Javapkg.integerBytes());
	}

	public void testFunction() {
		Javapkg.systemCurrentTimeMillis();
	}

	public void testMethod() {
		try {
			assertEquals("Integer.decode", 0xff, Javapkg.integerDecode("0xff"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Exception exc = null;
		try {
			Javapkg.integerDecode("obviously wrong");
		} catch (Exception e) {
			exc = e;
		}
		assertNotNull("IntegerDecode Exception", exc);
	}

	public void testOverloadedMethod() {
		try {
			assertEquals("Integer.parseInt", 0xc4, Javapkg.integerParseInt("c4", 16));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Exception exc = null;
		try {
			Javapkg.integerParseInt("wrong", 16);
		} catch (Exception e) {
			exc = e;
		}
		assertNotNull("integerParseInt Exception", exc);
		assertEquals("Integer.valueOf", 42, Javapkg.integerValueOf(42));
	}

	public void testException() {
		Exception exc = null;
		try {
			Javapkg.provokeRuntimeException();
		} catch (Exception e) {
			exc = e;
		}
		assertNotNull("RuntimeException", exc);
	}
}
