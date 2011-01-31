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

public class Passager extends Ice.ObjectImpl
{
    public Passager()
    {
    }

    public Passager(int id, String sex, String nom, String email, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String provenance, String destination, String nationalite)
    {
        this.id = id;
        this.sex = sex;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.phone = phone;
        this.typeReclamateur = typeReclamateur;
        this.numVol = numVol;
        this.provenance = provenance;
        this.destination = destination;
        this.nationalite = nationalite;
    }

    private static class __F implements Ice.ObjectFactory
    {
        public Ice.Object
        create(String type)
        {
            assert(type.equals(ice_staticId()));
            return new Passager();
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
        "::DQSP::server::Passager",
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
        __os.writeInt(id);
        __os.writeString(sex);
        __os.writeString(nom);
        __os.writeString(email);
        __os.writeString(adresse);
        __os.writeString(codePostale);
        __os.writeString(phone);
        __os.writeString(typeReclamateur);
        __os.writeString(numVol);
        __os.writeString(provenance);
        __os.writeString(destination);
        __os.writeString(nationalite);
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
        id = __is.readInt();
        sex = __is.readString();
        nom = __is.readString();
        email = __is.readString();
        adresse = __is.readString();
        codePostale = __is.readString();
        phone = __is.readString();
        typeReclamateur = __is.readString();
        numVol = __is.readString();
        provenance = __is.readString();
        destination = __is.readString();
        nationalite = __is.readString();
        __is.endReadSlice();
        super.__read(__is, true);
    }

    public void
    __write(Ice.OutputStream __outS)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::Passager was not generated with stream support";
        throw ex;
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::Passager was not generated with stream support";
        throw ex;
    }

    public int id;

    public int
    getId()
    {
        return id;
    }

    public void
    setId(int _id)
    {
        id = _id;
    }

    public String sex;

    public String
    getSex()
    {
        return sex;
    }

    public void
    setSex(String _sex)
    {
        sex = _sex;
    }

    public String nom;

    public String
    getNom()
    {
        return nom;
    }

    public void
    setNom(String _nom)
    {
        nom = _nom;
    }

    public String email;

    public String
    getEmail()
    {
        return email;
    }

    public void
    setEmail(String _email)
    {
        email = _email;
    }

    public String adresse;

    public String
    getAdresse()
    {
        return adresse;
    }

    public void
    setAdresse(String _adresse)
    {
        adresse = _adresse;
    }

    public String codePostale;

    public String
    getCodePostale()
    {
        return codePostale;
    }

    public void
    setCodePostale(String _codePostale)
    {
        codePostale = _codePostale;
    }

    public String phone;

    public String
    getPhone()
    {
        return phone;
    }

    public void
    setPhone(String _phone)
    {
        phone = _phone;
    }

    public String typeReclamateur;

    public String
    getTypeReclamateur()
    {
        return typeReclamateur;
    }

    public void
    setTypeReclamateur(String _typeReclamateur)
    {
        typeReclamateur = _typeReclamateur;
    }

    public String numVol;

    public String
    getNumVol()
    {
        return numVol;
    }

    public void
    setNumVol(String _numVol)
    {
        numVol = _numVol;
    }

    public String provenance;

    public String
    getProvenance()
    {
        return provenance;
    }

    public void
    setProvenance(String _provenance)
    {
        provenance = _provenance;
    }

    public String destination;

    public String
    getDestination()
    {
        return destination;
    }

    public void
    setDestination(String _destination)
    {
        destination = _destination;
    }

    public String nationalite;

    public String
    getNationalite()
    {
        return nationalite;
    }

    public void
    setNationalite(String _nationalite)
    {
        nationalite = _nationalite;
    }
}
