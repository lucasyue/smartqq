/*******************************************************************************
 * Copyright 2017 Bstek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.bstek.urule.runtime.assertor;

import java.util.Collection;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;

/**
 * @author lucas.yue
 * @since 2018-01-10
 */
public class ContainAssertor implements Assertor {
	public boolean eval(Object left, Object right,Datatype datatype) {
		if(left==null || right==null){
			return false;
		}
		if(left instanceof String){
			String leftStr=left.toString();
			if(right instanceof Collection){
				Collection<?> rightList=(Collection<?>)right;
				boolean rs = false;
				for(Object rO : rightList){
					String rightStr=rO.toString();
					rs = leftStr.contains(rightStr);
					if(rs){
						break;
					}
				}
				return rs;
			} else {
				String rightStr=right.toString();
				if(rightStr.contains(",")){
					String[] rightList=rightStr.split(",");
					for(String r : rightList){
						return leftStr.contains(r);
					}
				} else {
					return leftStr.contains(rightStr);
				}
			}
		}
		if(left instanceof Collection){
			Collection<?> list=(Collection<?>)left;
			if(right instanceof Collection){
				Collection<?> rightList=(Collection<?>)right;
				return list.containsAll(rightList);
			}else{
				return list.contains(right);				
			}
		}
		String leftStr=left.toString();
		String rightStr=right.toString();
		return leftStr.contains(rightStr);
	}

	public boolean support(Op op) {
		return op.equals(Op.Contain);
	}
}
