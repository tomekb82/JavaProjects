
class Comparator{
private class TUComparator implements Comparator<CC> {

		@Override
		public int compare(CC tu1, CC tu2) {
			if (...)
				return -1;
			else if (...)
				return 1;
			else if (...)
				return 0;
			
		}

		
	}

public List<CC> gA() {
		// pobiera pudełka, które nie stoją na maszynie gotowe do użycia
		List<CC> withDestination = tuService
				.getCC();
		TUComparator comparator;
		if (startLocation != null)
			comparator = new TUComparator(startLocation, sectors);
		else
			comparator = new TUComparator(sectors);
		Collections.sort(withDestination, comparator);
		return withDestination;
	
	}

}
