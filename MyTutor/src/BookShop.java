
import java.util.Scanner;
import java.util.ArrayList;

public class BookShop {

	private static float budget = 500;
	ArrayList<Book> stock = new ArrayList<Book>();

	static class Book {
		String book_type = "";
		float book_price = 0;
		int book_stock = 0;
		int book_sales = 0;
		final float supplier_cost = 0.7f;

		public Book(String type, int price, int stock) {
			this.book_type = type;
			this.book_price = price;
			this.book_stock = stock;
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int y = 0;
		int user_choice = 2;
		boolean quit = false;

		BookShop b = new BookShop();
		Book book_a = new BookShop.Book("A", 25, 10);
		Book book_b = new BookShop.Book("B", 20, 10);
		Book book_c = new BookShop.Book("C", 23, 10);
		Book book_d = new BookShop.Book("D", 30, 10);
		Book book_e = new BookShop.Book("E", 27, 10);

		do {
			// Display menu
			System.out.println();
			System.out.println("1) Sell a book");
			System.out.println("2) Print out Bookshop Balance");
			System.out.println("3) Quit");
			System.out.println();
			System.out.print("Enter choice [1-3]: ");
			user_choice = s.nextInt();
			switch (user_choice) {
			case 1:
				System.out.println("Enter book type [A B C D E]: ");
				String op;

				op = s.next(); // type of book

				break;
			case 2:

			case 3:

				System.out.println("Thanks for coming!");
				quit = true;
				s.close();
				break;
			default:
				System.out.println("\nInvalid Choice.");
			}
		} while (!quit);
	}

}
