public class GenericExample<T> 
{
	public void print() 
	{
		System.out.println("T");
	}

	public static void main(String[] args)
	{
		GenericExample<String> sge = new GenericExample<>();
		sge.print();
	}
}