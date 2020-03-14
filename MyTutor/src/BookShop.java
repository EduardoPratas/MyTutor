
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;
import java.util.Comparator;

public class BookShop {

	private static float budget = 500;
	ArrayList<Book> stock = new ArrayList<Book>();

	public int sell_book(String type, int quantity) {
		int op_result = -1;
		for (Book b : stock) {
			if (b.get_book_type().equals(type)) {
				op_result = b.sell_Book(quantity);
				if (op_result == 1)
					return 1;
			}

		}
		return op_result;
	}

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

		public int sell_Book(int quantity) {

			if (quantity > book_stock) {
				return 0;
			} else {

				book_stock -= quantity;
				book_sales += quantity;

				if (book_stock < 3) {
					book_stock += 10;
				} // Below 3 order 10 more copies

				budget += quantity*(this.book_price * (1 - supplier_cost));
				return 1;
			}
		}

		public float get_profit() {
			return book_sales * book_price * (1 - supplier_cost);
		}

		public String get_book_type() {
			return this.book_type;
		}

		public int get_sales() {
			return this.book_sales;
		}

	}

	public void addBook(Book b) {
		stock.add(b);

	}

	public void show_book_selling_report() {

		DecimalFormat df = new DecimalFormat("0");

		// Compare by sales and then profit
		Comparator<Book> compareBooks = Comparator.comparing(Book::get_sales).thenComparing(Book::get_profit);

		Collections.sort(stock, compareBooks);

		System.out.println("\nMyTutor Bookshop Balance: £" + df.format(budget));

		
		for(int i=stock.size()-1;i>0; i--) {
			System.out.println("Book " + stock.get(i).book_type + " | " + stock.get(i).book_sales + " " + "Copies Sold | £"
					+ df.format(stock.get(i).get_profit()) + " Total Profit");
		}

	}

	public void show_book_stock() {

		System.out.println("\nMyTutor Book Stock");

		for (Book b : stock) {
			System.out.println("Book " + b.book_type + " | " + b.book_stock + " " + "Copies");
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
		b.addBook(book_a);
		b.addBook(book_b);
		b.addBook(book_c);
		b.addBook(book_d);
		b.addBook(book_e);

		do {
			// Display menu
			System.out.println();
			System.out.println("1) Sell a book");
			System.out.println("2) Print out Bookshop Balance");
			System.out.println("3) Print out Book Stock");
			System.out.println("4) Quit");
			System.out.println();
			System.out.print("Enter choice [1-4]: ");
			user_choice = s.nextInt();
			switch (user_choice) {
			case 1:
				System.out.println("Enter book type [A B C D E]: ");
				String op, book_type;
				int op_result;

				op = s.next(); // type of book
				book_type = op.toUpperCase();

				if (book_type.equals("A") || book_type.equals("B") || book_type.equals("C") || book_type.equals("D")
						|| book_type.equals("E")) {
					// System.out.println("Selected option: " + book_type);

				} else {
					System.out.println(op + " is an invalid option!");
					break;
				}

				System.out.println("Enter book quantity: ");
				int quantity = s.nextInt();

				if (quantity > 0) {

					System.out.println("Book type: " + book_type + " | Quantity: " + quantity);
					op_result = b.sell_book(book_type, quantity);
					if (op_result == 1)
						System.out.println("Thank you for your purchase! ");
					else if (op_result == 0)
						System.out.println("Sorry, we are out of stock. ");
				}
				break;
			case 2:
				b.show_book_selling_report();
				break;
			case 3:
				b.show_book_stock();
				break;
			case 4:
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
