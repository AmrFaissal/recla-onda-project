// **********************************************************************
//
// Copyright (c) 2003-2009 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.1

package DQSP.server;

public class AnalyseTable extends Ice.ObjectImpl
{
    public AnalyseTable()
    {
    }

    public AnalyseTable(String theme, int nbrRecla, String action, String descriptif, boolean validation)
    {
        this.theme = theme;
        this.nbrRecla = nbrRecla;
        this.action = action;
        this.descriptif = descriptif;
        this.validation = validation;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object
        create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new AnalyseTable();
        }

        public void
        destroy()
        {
        }
    }
    private static Ice.ObjectFactory _factory = new __F();

    public static Ice.ObjectFactory
    ice_factory()
    {
        return _factory;
    }

    public static final String[] __ids =
    {
        "::DQSP::server::AnalyseTable",
        "::Ice::Object"
    };

    public boolean
    ice_isA(String s)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
        return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
        return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
        return __ids;
    }

    public String
    ice_id()
    {
        return __ids[0];
    }

    public String
    ice_id(Ice.Current __current)
    {
        return __ids[0];
    }

    public static String
    ice_staticId()
    {
        return __ids[0];
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeTypeId(ice_staticId());
        __os.startWriteSlice();
        __os.writeString(theme);
        __os.writeInt(nbrRecla);
        __os.writeString(action);
        __os.writeString(descriptif);
        __os.writeBool(validation);
        __os.endWriteSlice();
        super.__write(__os);
    }

    public void
    __read(IceInternal.BasicStream __is, boolean __rid)
    {
        if(__rid)
        {
            __is.readTypeId();
        }
        __is.startReadSlice();
        theme = __is.readString();
        nbrRecla = __is.readInt();
        action = __is.readString();
        descriptif = __is.readString();
        validation = __is.readBool();
        __is.endReadSlice();
        super.__read(__is, true);
    }

    public void
    __write(Ice.OutputStream __outS)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::AnalyseTable was not generated with stream support";
        throw ex;
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::AnalyseTable was not generated with stream support";
        throw ex;
    }

    public String theme;

    public String
    getTheme()
    {
        return theme;
    }

    public void
    setTheme(String _theme)
    {
        theme = _theme;
    }

    public int nbrRecla;

    public int
    getNbrRecla()
    {
        return nbrRecla;
    }

    public void
    setNbrRecla(int _nbrRecla)
    {
        nbrRecla = _nbrRecla;
    }

    public String action;

    public String
    getAction()
    {
        return action;
    }

    public void
    setAction(String _action)
    {
        action = _action;
    }

    public String descriptif;

    public String
    getDescriptif()
    {
        return descriptif;
    }

    public void
    setDescriptif(String _descriptif)
    {
        descriptif = _descriptif;
    }

    public boolean validation;

    public boolean
    getValidation()
    {
        return validation;
    }

    public void
    setValidation(boolean _validation)
    {
        validation = _validation;
    }

    public boolean
    isValidation()
    {
        return validation;
    }
}
