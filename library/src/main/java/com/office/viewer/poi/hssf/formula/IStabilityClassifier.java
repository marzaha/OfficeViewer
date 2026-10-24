/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package com.office.viewer.poi.hssf.formula;


public interface IStabilityClassifier {

	/**
	 * Convenience implementation for situations where all cell definitions remain fixed after
	 * evaluation begins.
	 */
	IStabilityClassifier TOTALLY_IMMUTABLE = new IStabilityClassifier() {
		public boolean isCellFinal(int sheetIndex, int rowIndex, int columnIndex) {
			return true;
		}
	};

	/**
	 * Checks if a cell's value(/formula) is fixed - in other words - not expected to be modified
	 * between calls to the evaluator. (Note - this is an independent concept from whether a 
	 * formula cell's evaluated value may change during successive calls to the evaluator).
	 * 
	 * @param sheetIndex zero based index into workbook sheet list
	 * @param rowIndex zero based row index of cell
	 * @param columnIndex zero based column index of cell
	 * @return <code>false</code> if the evaluating application may need to modify the specified 
	 * cell between calls to the evaluator. 
	 */
	boolean isCellFinal(int sheetIndex, int rowIndex, int columnIndex);
}
