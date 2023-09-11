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

/**
 * easy fst
 *
 * @author jinhong zhou
 */
public class Efst {

	public byte[] bytes;
	
	Arc root = new Arc((byte)0);
	int addCount = 0;
	
	Arc lastFinalArc = null;//最后结束的Arc
	Arc latestFinalArc = null;//最新结束的Arc

	public Efst() {
		bytes = new byte[128];
	}

	public void add(byte[] srcBytes, long num) {
		lastFinalArc = latestFinalArc;
		Arc nowArc = root;
		for (int x = 0; x < srcBytes.length; x++) {
			nowArc = nowArc.add(srcBytes[x]);

			if (x == srcBytes.length - 1) {
				nowArc.vlong = Vlong.getVlong(num);
				latestFinalArc = nowArc;
				nowArc.node = -1;
			}
		}
		addCount++;
	}

	public Vlong find(byte[] findBytes) {
		Vlong vlong = null;
		Arc findArc = root;
		for (int x = 0; x < findBytes.length; x++) {
			findArc = findArc.find(findBytes[x]);
			if(null == findArc) {
				return vlong;
			}
			
			if (x == findBytes.length - 1) {
				vlong = findArc.vlong;
			}
		}
		return vlong;
	}
	
	private void freezeTail() {
		if(null == lastFinalArc.nextArc);
	}
}
