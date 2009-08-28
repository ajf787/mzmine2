/*
 * Copyright 2006-2009 The MZmine 2 Development Team
 * 
 * This file is part of MZmine 2.
 * 
 * MZmine 2 is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine 2 is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine 2; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

package net.sf.mzmine.modules.alignment.join;

import net.sf.mzmine.data.ChromatographicPeak;
import net.sf.mzmine.data.IsotopePattern;
import net.sf.mzmine.data.PeakListRow;
import net.sf.mzmine.modules.isotopes.isotopepatternscore.IsotopePatternScoreCalculator;
import net.sf.mzmine.util.PeakUtils;

/**
 * This class represents a score between peak list row and aligned peak list row
 */
class RowVsRowScore implements Comparable<RowVsRowScore> {

	private PeakListRow peakListRow, alignedRow;
	double score;
	private String errorMessage;

	RowVsRowScore(PeakListRow peakListRow, PeakListRow alignedRow,
			double mzMaxDiff, double mzWeight, double rtMaxDiff,
			double rtWeight, double sameIDWeight,
			boolean compareIsotopePattern, double isotopePatternScoreThresold,
			double sameIsotopePatternWeight) throws Exception {

		this.peakListRow = peakListRow;
		this.alignedRow = alignedRow;

		// Calculate differences between m/z and RT values
		double mzDiff = Math.abs(peakListRow.getAverageMZ()
				- alignedRow.getAverageMZ());

		double rtDiff = Math.abs(peakListRow.getAverageRT()
				- alignedRow.getAverageRT());

		// Compare identities
		double sameIDFlag = 0;
		if (PeakUtils.compareIdentities(peakListRow, alignedRow))
			sameIDFlag = 1;

		// Compare isotope pattern 
		double sameIsotopePatternScore = 0;
		if (compareIsotopePattern){
			
			ChromatographicPeak p1 = peakListRow.getBestIsotopePatternPeak();
			ChromatographicPeak p2 = alignedRow.getBestIsotopePatternPeak();
			
			IsotopePattern ip1 = p1.getIsotopePattern();
			IsotopePattern ip2 = p2.getIsotopePattern();

			sameIsotopePatternScore = IsotopePatternScoreCalculator.getSimilarityScore(ip1, ip2);
			
			if (sameIsotopePatternScore < isotopePatternScoreThresold){
				sameIsotopePatternWeight = 0;
			}
			
		}
		else{
			sameIsotopePatternWeight = 0;
		}

		score = ((1 - mzDiff / mzMaxDiff) * mzWeight) + 
				((1 - rtDiff / rtMaxDiff) * rtWeight) + 
				(sameIDFlag * sameIDWeight) + 
				(sameIsotopePatternScore * sameIsotopePatternWeight);

	}

	/**
	 * This method returns the peak list row which is being aligned
	 */
	PeakListRow getPeakListRow() {
		return peakListRow;
	}

	/**
	 * This method returns the row of aligned peak list
	 */
	PeakListRow getAlignedRow() {
		return alignedRow;
	}

	/**
	 * This method returns score between the these two peaks (the lower score,
	 * the better match)
	 */
	double getScore() {
		return score;
	}
	
	String getErrorMessage(){
		return errorMessage;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(RowVsRowScore object) {

		// We must never return 0, because the TreeSet in JoinAlignerTask would
		// treat such elements as equal
		if (score < object.getScore())
			return 1;
		else
			return -1;

	}

}
