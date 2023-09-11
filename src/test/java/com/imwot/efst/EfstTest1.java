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
 * 〈一句话功能简述〉
 *
 * @author jinhong zhou
 */
public class EfstTest1 {

	/**
	 * 未排序数据
	 */

	public static void main(String[] args) throws Exception {
		String inputValues[] = { "mop", "pop", "moth", "star", "stop", "top", "mopa"};
		long outputValues[] = { 9, 1, 8, 3, 1, 5, 2};
		Efst fst = new Efst();
		for (int i = 0; i < inputValues.length; i++) {
			fst.add(inputValues[i].getBytes("UTF-8"), outputValues[i]);
		}

		System.out.println("****************华丽的分割线*************");

		Vlong[] vlong=new Vlong[inputValues.length];
		int error = 0;
		int success = 0;
		for (int i = 0; i < inputValues.length; i++) {
			vlong[i] = fst.find(inputValues[i].getBytes("UTF-8"));
			if(vlong[i].getValue()==outputValues[i]) {
				success++;
			}else {
				error++;
			}
			System.out.println(inputValues[i] +"="+vlong[i].toString()+"="+(vlong[i].getValue()==outputValues[i]));
		}
		
		String txt = "mopb";
		Vlong vlong1 = fst.find(txt.getBytes("UTF-8"));
		System.out.println(txt + "=" + (null == vlong1 ? "未找到":vlong1.toString()));
		System.out.println("fst.count:" + fst.addCount);
		System.out.println("fst.error:" + error);
		System.out.println("fst.success:" + success);

	}
}
//ByteArrayDataInput
