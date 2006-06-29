/*
 * Copyright 2006 The MZmine Development Team
 *
 * This file is part of MZmine.
 *
 * MZmine is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MZmine is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MZmine; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

package net.sf.mzmine.interfaces;

import java.util.Hashtable;

/**
 * This interface defines the properties of a detected peak
 */
public interface IsotopePattern {

	/**
	 * Returns the charge state of peaks in the pattern
	 */
	public int getChargeState();

	/**
	 * Returns identification assigned to this isotope pattern, or null if no identification has been assigned.
	 */
	// TODO : Maybe identification results should be assigned to alignment result rows instead of isotope patterns!!!
	public CompoundIdentity getIdentity();
}