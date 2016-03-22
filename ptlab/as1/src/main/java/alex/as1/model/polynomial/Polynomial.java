package alex.as1.model.polynomial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

public class Polynomial implements Polynomializable {

	private ArrayList<Monomial> poly;

	public Polynomial() {
		poly = new ArrayList<Monomial>();
	}

	public Polynomial(Polynomializable polyn) {
		this();
		setMonomials(polyn);
	}

	private static final String REGEX_SEPARATOR = " ";

	/**
	 * @param polynomialRegex
	 *            May only accept integer coefficients and non-negative powers. the
	 *            parameter can be any permutation of monomials separated by
	 *            space.
	 */
	public static Polynomial parsePolynomial(String polynomialRegex) {
		ArrayList<Monomial> monomials = new ArrayList<Monomial>();

		String regex = new String(polynomialRegex);
		// Cut out the first element if it is a space (if it begins with a
		// 'space'sign
		if (regex.charAt(0) == ' ') {
			regex = regex.substring(1);
		}

		// Parse each individual monomial
		String[] stringMonomials = regex.split(REGEX_SEPARATOR);

		for (String monomialRegex : stringMonomials) {
			Monomial monomial = Monomial.parseMonomial(monomialRegex);
			monomials.add(monomial);
		}

		Polynomial result = new Polynomial();
		result.addMonomials(monomials);
		return result;
	}

	public void clear() {
		poly.clear();
	}

	public void addMonomial(Monomial monomial) {
		poly.add(monomial);
		normalize();
	}

	public void addMonomials(ArrayList<Monomial> monomials) {
		poly.addAll(monomials);
		normalize();
	}

	public void setMonomials(Polynomializable polyn) {
		clear();
		addMonomials(polyn.getPolynomial().getMonomials());
	}

	public ArrayList<Monomial> getMonomials() {
		return poly;
	}

	@Override
	public Polynomial getPolynomial() {
		return this;
	}

	// Check if no two monomials have the same power and if
	// they are sorted descending
	private boolean normalized() {
		int size = poly.size();
		boolean normal = true;

		for (int i = 1; i < size && normal; i++) {
			if (poly.get(i - 1).getDegree() >= poly.get(i).getDegree()) {
				normal = false;
			}
		}

		return normal;
	}

	// Brings the polynomial in the format described above
	// Polynomials having the same degree will be added (operations also exploit
	// this)
	private void normalize() {
		if (normalized()) {
			return;
		}

		ArrayList<Monomial> normalizedPoly = new ArrayList<Monomial>();
		ArrayList<Monomial> toBeDeleted = new ArrayList<Monomial>();

		// Sum all monomials having the same degree
		int size = poly.size();
		while (size > 0) {
			toBeDeleted.clear();

			Monomial firstMonomial = poly.get(0);
			toBeDeleted.add(firstMonomial);
			BigDecimal newCoefficient = firstMonomial.getCoefficient();
			for (int i = 1; i < size; i++) {
				Monomial sameDegreeMonomial = poly.get(i);
				if (firstMonomial.getDegree() == sameDegreeMonomial.getDegree()) {
					toBeDeleted.add(sameDegreeMonomial);
					newCoefficient = newCoefficient.add(sameDegreeMonomial.getCoefficient());
				}
			}

			if (newCoefficient.doubleValue() != 0) {
				Monomial newMonomial = new Monomial(firstMonomial.getDegree(), newCoefficient);
				normalizedPoly.add(newMonomial);
			}

			for (Monomial del : toBeDeleted) {
				poly.remove(del);
			}
			size = poly.size();
		}

		poly = normalizedPoly;
		poly.sort(new MonomialComparator());
	}

	// Used to sort
	private class MonomialComparator implements Comparator<Monomial> {

		public int compare(Monomial o1, Monomial o2) {
			if (o1.getDegree() > o2.getDegree()) {
				return -1;
			} else if (o1.getDegree() < o2.getDegree()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public int getDegree() {
		int maxDegree = -1;

		// The polynomial will always be normalized so the first monomial in the
		// collection will have the highest degree
		if (poly.size() > 0) {
			maxDegree = poly.get(0).getDegree();
		}

		return maxDegree;
	}

	public Polynomial clone() {
		Polynomial result = new Polynomial();

		result.addMonomials(getMonomials());

		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Monomial monomial : poly) {
			sb.append(monomial.toString());
			// System.out.println("ts: " + monomial.toString());
		}
		if (poly.size() == 0) {
			sb.append("0");
		}

		// Remove the '+' if it is the first symbol
		String result = sb.toString();
		if (result.charAt(0) == '+') {
			result = result.substring(1);
		}

		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		
		if (obj instanceof Polynomial) {
			Polynomial p = (Polynomial) obj;
			
			if (this.getMonomials().size() != p.getMonomials().size()) {
				result = false;
			} else {
				for (int i = 0; i < this.getMonomials().size(); i++) {
					if (!this.getMonomials().get(i).equals(p.getMonomials().get(i))) {
						result = false;
					}
				}
			}
			
		} else {
			result = false;
		}
		
		return result;
	}
}
