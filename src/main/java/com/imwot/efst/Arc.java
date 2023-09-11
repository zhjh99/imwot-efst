/**
 [The "BSD license"]
 Copyright (c) 2013-2018 jinhong zhou
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
     notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
     derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.imwot.efst;

import java.util.Arrays;

/**
 * 
 *
 * @author jinhong zhou
 */
public class Arc {

	// Final arc： 1 表示这是一个 Final arc，arc 指向一个终止状态 E。
	// Last arc：2 表示 arc 是当前 node节点的最后一个 arc；
	// Target next：4 当前 arc 的下一个 arc 是当前节点最后一个 arc 的向 前的一个arc（数组位置，向前邻接的那个 arc）。
	// Stop node：8 终止状态 E。 Has output: 16 当前 arc 有 output值（表示 output 不为 0）
	int node;
	byte[] key;
	Vlong vlong;
	Arc parentArc;
	Arc[] nextArc;
	private int arcActualLength;

	public Arc(byte[] key) {
		this.key = key;
		this.arcActualLength = 0;
		this.nextArc = new Arc[8];
	}

	public Arc add(byte[] oneByte) {
		if (arcActualLength == nextArc.length) {
			int newLength = arcActualLength + 8;
			nextArc = Arrays.copyOf(nextArc, newLength);
		}
		Arc find = find(oneByte);

		if (find == null) {
			Arc arc = new Arc(oneByte);
			find = arc;
			nextArc[arcActualLength] = arc;
			arcActualLength++;
			arc.parentArc = this;
		}
		return find;
	}

	public Arc add(byte oByte) {
		byte[] oneByte = new byte[] { oByte };
		if (arcActualLength == nextArc.length) {
			int newLength = arcActualLength + 8;
			nextArc = Arrays.copyOf(nextArc, newLength);
		}
		Arc find = find(oneByte);

		if (find == null) {
			Arc arc = new Arc(oneByte);
			find = arc;
			nextArc[arcActualLength] = arc;
			arcActualLength++;
			arc.parentArc = this;
		}
		return find;
	}

	public Arc find(byte[] key) {
		Arc find = null;
		for (int x = 0; x < nextArc.length; x++) {
			Arc next = nextArc[x];
			if (null != next && Arrays.equals(next.key, key)) {
				find = next;
			}
		}
		return find;
	}

	public Arc find(byte key) {
		byte[] keys = new byte[] { key };
		Arc find = null;
		for (int x = 0; x < nextArc.length; x++) {
			Arc next = nextArc[x];
			if (null != next && Arrays.equals(next.key, keys)) {
				find = next;
			}
		}
		return find;
	}
}
