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
 * MZmine 2; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA
 */

package net.sf.mzmine.data;

/**
 * 
 */
public interface PeakListRow {

	/**
	 * Return raw datas with peaks on this row
	 */
	public RawDataFile[] getRawDataFiles();

	/**
	 * Returns ID of this row
	 */
	public int getID();

	/**
	 * Returns number of peaks assigned to this row
	 */
	public int getNumberOfPeaks();

	/**
	 * Return peaks assigned to this row
	 */
	public ChromatographicPeak[] getPeaks();

	/**
	 * Returns peak for given raw data file
	 */
	public ChromatographicPeak getPeak(RawDataFile rawData);

	/**
	 * Add a peak
	 */
	public void addPeak(RawDataFile rawData, ChromatographicPeak peak);

	/**
	 * Has a peak?
	 */
	public boolean hasPeak(ChromatographicPeak peak);

	/**
	 * Returns average M/Z for peaks on this row
	 */
	public double getAverageMZ();

	/**
	 * Returns average RT for peaks on this row
	 */
	public double getAverageRT();

	/**
	 * Returns comment for this row
	 */
	public String getComment();

	/**
	 * Sets comment for this row
	 */
	public void setComment(String comment);

	/**
	 * Add a new identity candidate (result of identification method)
	 * 
	 * @param identity
	 *            New peak identity
	 * @param preffered
	 *            boolean value to define this identity as preferred identity
	 */
	public void addPeakIdentity(PeakIdentity identity, boolean preffered);

	/**
	 * Remove identity candidate
	 * 
	 * @param identity
	 *            Peak identity
	 */
	public void removePeakIdentity(PeakIdentity identity);

	/**
	 * Returns all candidates for this peak's identity
	 * 
	 * @return Identity candidates
	 */
	public PeakIdentity[] getPeakIdentities();

	/**
	 * Returns preferred peak identity among candidates
	 * 
	 * @return Preferred identity
	 */
	public PeakIdentity getPreferredPeakIdentity();

	/**
	 * Sets a preferred peak identity among candidates
	 * 
	 * @param identity
	 *            Preferred identity
	 */
	public void setPreferredPeakIdentity(PeakIdentity identity);

	/**
	 * Returns maximum raw data point intensity among all peaks in this row
	 * 
	 * @return Maximum intensity
	 */
	public double getDataPointMaxIntensity();

	/**
	 * Returns the most intense peak in this row
	 */
	public ChromatographicPeak getBestPeak();

	/**
	 * Returns the most intense peak in this row which has an isotope pattern
	 * attached. If there are no isotope patterns present in the row, returns
	 * null.
	 */
	public ChromatographicPeak getBestIsotopePatternPeak();

}
