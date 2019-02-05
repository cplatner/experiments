using System;

/// <summary>
/// Originally written around 2010-11-08 explore the differences between implicit
/// explicit interface implementation.  Decent discussion here:
/// https://stackoverflow.com/questions/598714/implicit-vs-explicit-interface-implementation
/// </summary>
public interface IInterface
{
    void Method();
}

/// <summary>Implement the interface implicitly</summary>
public class ImplicitClass : IInterface
{
    public void Method()
    {
        Console.WriteLine("implicit");
    }
}

/// <summary>Implement the interface explicitly</summary>
public class ExplicitClass : IInterface
{
    void IInterface.Method()
    {
        Console.WriteLine("explicit");
    }
}

public class ImplicitVsExplicit
{
    public static void Main()
    {
        //* MyImplicitClass instance would work with either the class or the Interface:
        ImplicitClass _class = new ImplicitClass();
        _class.Method();
        IInterface _interface = new ImplicitClass();
        _interface.Method();

        //* ExplicitClass would work only with the interface:
        ExplicitClass obj = new ExplicitClass();
        //* The following line would not work and will generate a compiler error.
        //obj.Method();
        //* Using 'var' will declare as the class, so this also will fail
        //var obj = new ExplicitClass();
        //obj.Method();

        //* This will work
        IInterface _explicit = new ExplicitClass();
        _explicit.Method();
    }
}
