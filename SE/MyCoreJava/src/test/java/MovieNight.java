import java.util.*;
import java.text.SimpleDateFormat;

public class MovieNight {
	public static Boolean canViewAll(Collection<Movie> movies) {
		ArrayList<Movie> al;
		al = new ArrayList<Movie>(movies);

		Collections.sort(al, new Comparator<Movie>() {
			@Override
			public int compare(Movie o1, Movie o2) {
				// TODO Auto-generated method stub
				if (o1.getStart()==null){
					return -1;
				}
				if (o2.getStart()==null){
					return 1;
				}
				return o1.getStart().compareTo(o2.getStart());
			}
		});
		
		Date end= null;
		for (Movie movie : al) {
			if (end!=null){
				Date start = movie.getStart(); 
				if (end.after(start)){
					return false;
				}
			}
			end = movie.getEnd();
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf
				.parse("2015-01-01 21:30")));
		movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf
				.parse("2015-01-01 23:00")));
		movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf
				.parse("2015-01-01 23:30")));

		System.out.println(MovieNight.canViewAll(movies));
	}
}

class Movie{
	private Date start, end;

	public Movie(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return this.start;
	}

	public Date getEnd() {
		return this.end;
	}
}