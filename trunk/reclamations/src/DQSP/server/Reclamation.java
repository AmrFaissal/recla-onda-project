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

public class Reclamation extends Ice.ObjectImpl
{
    public Reclamation()
    {
    }

    public Reclamation(int idReclamation, int idPassager, String date, String nomService, String nomAeroport, String terminale, String descriptif, String remarque)
    {
        this.idReclamation = idReclamation;
        this.idPassager = idPassager;
        this.date = date;
        this.nomService = nomService;
        this.nomAeroport = nomAeroport;
        this.terminale = terminale;
        this.descriptif = descriptif;
        this.remarque = remarque;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object
        create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new Reclamation();
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
        "::DQSP::server::Reclamation",
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
        __os.writeInt(idReclamation);
        __os.writeInt(idPassager);
        __os.writeString(date);
        __os.writeString(nomService);
        __os.writeString(nomAeroport);
        __os.writeString(terminale);
        __os.writeString(descriptif);
        __os.writeString(remarque);
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
        idReclamation = __is.readInt();
        idPassager = __is.readInt();
        date = __is.readString();
        nomService = __is.readString();
        nomAeroport = __is.readString();
        terminale = __is.readString();
        descriptif = __is.readString();
        remarque = __is.readString();
        __is.endReadSlice();
        super.__read(__is, true);
    }

    public void
    __write(Ice.OutputStream __outS)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::Reclamation was not generated with stream support";
        throw ex;
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::Reclamation was not generated with stream support";
        throw ex;
    }

    public int idReclamation;

    public int
    getIdReclamation()
    {
        return idReclamation;
    }

    public void
    setIdReclamation(int _idReclamation)
    {
        idReclamation = _idReclamation;
    }

    public int idPassager;

    public int
    getIdPassager()
    {
        return idPassager;
    }

    public void
    setIdPassager(int _idPassager)
    {
        idPassager = _idPassager;
    }

    public String date;

    public String
    getDate()
    {
        return date;
    }

    public void
    setDate(String _date)
    {
        date = _date;
    }

    public String nomService;

    public String
    getNomService()
    {
        return nomService;
    }

    public void
    setNomService(String _nomService)
    {
        nomService = _nomService;
    }

    public String nomAeroport;

    public String
    getNomAeroport()
    {
        return nomAeroport;
    }

    public void
    setNomAeroport(String _nomAeroport)
    {
        nomAeroport = _nomAeroport;
    }

    public String terminale;

    public String
    getTerminale()
    {
        return terminale;
    }

    public void
    setTerminale(String _terminale)
    {
        terminale = _terminale;
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

    public String remarque;

    public String
    getRemarque()
    {
        return remarque;
    }

    public void
    setRemarque(String _remarque)
    {
        remarque = _remarque;
    }
}
