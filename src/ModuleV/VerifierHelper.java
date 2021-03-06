package ModuleV;


/**
* ModuleV/VerifierHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ModuleV.idl
* Monday, May 18, 2015 5:01:17 PM ALMT
*/

abstract public class VerifierHelper
{
  private static String  _id = "IDL:ModuleV/Verifier:1.0";

  public static void insert (org.omg.CORBA.Any a, ModuleV.Verifier that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ModuleV.Verifier extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ModuleV.VerifierHelper.id (), "Verifier");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ModuleV.Verifier read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_VerifierStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ModuleV.Verifier value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ModuleV.Verifier narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ModuleV.Verifier)
      return (ModuleV.Verifier)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ModuleV._VerifierStub stub = new ModuleV._VerifierStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ModuleV.Verifier unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ModuleV.Verifier)
      return (ModuleV.Verifier)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ModuleV._VerifierStub stub = new ModuleV._VerifierStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
