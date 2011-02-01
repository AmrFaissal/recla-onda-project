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

public abstract class _DQSPServerDisp extends Ice.ObjectImpl implements DQSPServer
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
        throws java.lang.CloneNotSupportedException
    {
        throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
        "::DQSP::server::DQSPServer",
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

    public final void
    addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite)
    {
        addPassager(idPassager, sex, nom, mail, adresse, codePostale, phone, typeReclamateur, numVol, prov, dest, nationalite, null);
    }

    public final void
    addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif)
    {
        addReclamation(idPassager, nomAeroport, terminale, nomService, remarque, descriptif, null);
    }

    public final java.util.List<AnalyseTable>
    getAllAnalysisTable()
    {
        return getAllAnalysisTable(null);
    }

    public final java.util.List<Reclamation>
    getAllReclamations()
    {
        return getAllReclamations(null);
    }

    public final java.util.List<VService>
    getAllServices()
    {
        return getAllServices(null);
    }

    public final java.util.List<VPassager>
    getMailingList()
    {
        return getMailingList(null);
    }

    public final java.util.List<AnalyseTable>
    getReclamationsSortedList()
    {
        return getReclamationsSortedList(null);
    }

    public final void
    updateActionsEntreprises(String theme, boolean validation)
    {
        updateActionsEntreprises(theme, validation, null);
    }

    public final void
    updateAnalyseTable(String theme, String action)
    {
        updateAnalyseTable(theme, action, null);
    }

    public static Ice.DispatchStatus
    ___getAllAnalysisTable(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.is().skipEmptyEncaps();
        IceInternal.BasicStream __os = __inS.os();
        java.util.List<AnalyseTable> __ret = __obj.getAllAnalysisTable(__current);
        aTableHelper.write(__os, __ret);
        __os.writePendingObjects();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___getAllReclamations(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.is().skipEmptyEncaps();
        IceInternal.BasicStream __os = __inS.os();
        java.util.List<Reclamation> __ret = __obj.getAllReclamations(__current);
        reclamationsListHelper.write(__os, __ret);
        __os.writePendingObjects();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___getReclamationsSortedList(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.is().skipEmptyEncaps();
        IceInternal.BasicStream __os = __inS.os();
        java.util.List<AnalyseTable> __ret = __obj.getReclamationsSortedList(__current);
        reclamationsSortedListHelper.write(__os, __ret);
        __os.writePendingObjects();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___getMailingList(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.is().skipEmptyEncaps();
        IceInternal.BasicStream __os = __inS.os();
        java.util.List<VPassager> __ret = __obj.getMailingList(__current);
        mailListHelper.write(__os, __ret);
        __os.writePendingObjects();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___getAllServices(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        __inS.is().skipEmptyEncaps();
        IceInternal.BasicStream __os = __inS.os();
        java.util.List<VService> __ret = __obj.getAllServices(__current);
        serviceListHelper.write(__os, __ret);
        __os.writePendingObjects();
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___updateAnalyseTable(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        String theme;
        theme = __is.readString();
        String action;
        action = __is.readString();
        __is.endReadEncaps();
        __obj.updateAnalyseTable(theme, action, __current);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___updateActionsEntreprises(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        String theme;
        theme = __is.readString();
        boolean validation;
        validation = __is.readBool();
        __is.endReadEncaps();
        __obj.updateActionsEntreprises(theme, validation, __current);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___addPassager(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        int idPassager;
        idPassager = __is.readInt();
        String sex;
        sex = __is.readString();
        String nom;
        nom = __is.readString();
        String mail;
        mail = __is.readString();
        String adresse;
        adresse = __is.readString();
        String codePostale;
        codePostale = __is.readString();
        String phone;
        phone = __is.readString();
        String typeReclamateur;
        typeReclamateur = __is.readString();
        String numVol;
        numVol = __is.readString();
        String prov;
        prov = __is.readString();
        String dest;
        dest = __is.readString();
        String nationalite;
        nationalite = __is.readString();
        __is.endReadEncaps();
        __obj.addPassager(idPassager, sex, nom, mail, adresse, codePostale, phone, typeReclamateur, numVol, prov, dest, nationalite, __current);
        return Ice.DispatchStatus.DispatchOK;
    }

    public static Ice.DispatchStatus
    ___addReclamation(DQSPServer __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
        __checkMode(Ice.OperationMode.Normal, __current.mode);
        IceInternal.BasicStream __is = __inS.is();
        __is.startReadEncaps();
        int idPassager;
        idPassager = __is.readInt();
        String nomAeroport;
        nomAeroport = __is.readString();
        String terminale;
        terminale = __is.readString();
        String nomService;
        nomService = __is.readString();
        String remarque;
        remarque = __is.readString();
        String descriptif;
        descriptif = __is.readString();
        __is.endReadEncaps();
        __obj.addReclamation(idPassager, nomAeroport, terminale, nomService, remarque, descriptif, __current);
        return Ice.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
        "addPassager",
        "addReclamation",
        "getAllAnalysisTable",
        "getAllReclamations",
        "getAllServices",
        "getMailingList",
        "getReclamationsSortedList",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "updateActionsEntreprises",
        "updateAnalyseTable"
    };

    public Ice.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
        int pos = java.util.Arrays.binarySearch(__all, __current.operation);
        if(pos < 0)
        {
            throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return ___addPassager(this, in, __current);
            }
            case 1:
            {
                return ___addReclamation(this, in, __current);
            }
            case 2:
            {
                return ___getAllAnalysisTable(this, in, __current);
            }
            case 3:
            {
                return ___getAllReclamations(this, in, __current);
            }
            case 4:
            {
                return ___getAllServices(this, in, __current);
            }
            case 5:
            {
                return ___getMailingList(this, in, __current);
            }
            case 6:
            {
                return ___getReclamationsSortedList(this, in, __current);
            }
            case 7:
            {
                return ___ice_id(this, in, __current);
            }
            case 8:
            {
                return ___ice_ids(this, in, __current);
            }
            case 9:
            {
                return ___ice_isA(this, in, __current);
            }
            case 10:
            {
                return ___ice_ping(this, in, __current);
            }
            case 11:
            {
                return ___updateActionsEntreprises(this, in, __current);
            }
            case 12:
            {
                return ___updateAnalyseTable(this, in, __current);
            }
        }

        assert(false);
        throw new Ice.OperationNotExistException(__current.id, __current.facet, __current.operation);
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeTypeId(ice_staticId());
        __os.startWriteSlice();
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
        __is.endReadSlice();
        super.__read(__is, true);
    }

    public void
    __write(Ice.OutputStream __outS)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::DQSPServer was not generated with stream support";
        throw ex;
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        Ice.MarshalException ex = new Ice.MarshalException();
        ex.reason = "type DQSP::server::DQSPServer was not generated with stream support";
        throw ex;
    }
}
