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

public final class DQSPServerPrxHelper extends Ice.ObjectPrxHelperBase implements DQSPServerPrx
{
    public void
    addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite)
    {
        addPassager(idPassager, sex, nom, mail, adresse, codePostale, phone, typeReclamateur, numVol, prov, dest, nationalite, null, false);
    }

    public void
    addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite, java.util.Map<String, String> __ctx)
    {
        addPassager(idPassager, sex, nom, mail, adresse, codePostale, phone, typeReclamateur, numVol, prov, dest, nationalite, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                __del.addPassager(idPassager, sex, nom, mail, adresse, codePostale, phone, typeReclamateur, numVol, prov, dest, nationalite, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public void
    addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif)
    {
        addReclamation(idPassager, nomAeroport, terminale, nomService, remarque, descriptif, null, false);
    }

    public void
    addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif, java.util.Map<String, String> __ctx)
    {
        addReclamation(idPassager, nomAeroport, terminale, nomService, remarque, descriptif, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                __del.addReclamation(idPassager, nomAeroport, terminale, nomService, remarque, descriptif, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public java.util.List<AnalyseTable>
    getAllAnalysisTable()
    {
        return getAllAnalysisTable(null, false);
    }

    public java.util.List<AnalyseTable>
    getAllAnalysisTable(java.util.Map<String, String> __ctx)
    {
        return getAllAnalysisTable(__ctx, true);
    }

    @SuppressWarnings("unchecked")
    private java.util.List<AnalyseTable>
    getAllAnalysisTable(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("getAllAnalysisTable");
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                return __del.getAllAnalysisTable(__ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public java.util.List<Reclamation>
    getAllReclamations()
    {
        return getAllReclamations(null, false);
    }

    public java.util.List<Reclamation>
    getAllReclamations(java.util.Map<String, String> __ctx)
    {
        return getAllReclamations(__ctx, true);
    }

    @SuppressWarnings("unchecked")
    private java.util.List<Reclamation>
    getAllReclamations(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("getAllReclamations");
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                return __del.getAllReclamations(__ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public java.util.List<VService>
    getAllServices()
    {
        return getAllServices(null, false);
    }

    public java.util.List<VService>
    getAllServices(java.util.Map<String, String> __ctx)
    {
        return getAllServices(__ctx, true);
    }

    @SuppressWarnings("unchecked")
    private java.util.List<VService>
    getAllServices(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("getAllServices");
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                return __del.getAllServices(__ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public java.util.List<VPassager>
    getMailingList()
    {
        return getMailingList(null, false);
    }

    public java.util.List<VPassager>
    getMailingList(java.util.Map<String, String> __ctx)
    {
        return getMailingList(__ctx, true);
    }

    @SuppressWarnings("unchecked")
    private java.util.List<VPassager>
    getMailingList(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("getMailingList");
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                return __del.getMailingList(__ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public java.util.List<AnalyseTable>
    getReclamationsSortedList()
    {
        return getReclamationsSortedList(null, false);
    }

    public java.util.List<AnalyseTable>
    getReclamationsSortedList(java.util.Map<String, String> __ctx)
    {
        return getReclamationsSortedList(__ctx, true);
    }

    @SuppressWarnings("unchecked")
    private java.util.List<AnalyseTable>
    getReclamationsSortedList(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("getReclamationsSortedList");
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                return __del.getReclamationsSortedList(__ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public void
    updateActionsEntreprises(String theme, boolean validation)
    {
        updateActionsEntreprises(theme, validation, null, false);
    }

    public void
    updateActionsEntreprises(String theme, boolean validation, java.util.Map<String, String> __ctx)
    {
        updateActionsEntreprises(theme, validation, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    updateActionsEntreprises(String theme, boolean validation, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                __del.updateActionsEntreprises(theme, validation, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public void
    updateAnalyseTable(String theme, String action)
    {
        updateAnalyseTable(theme, action, null, false);
    }

    public void
    updateAnalyseTable(String theme, String action, java.util.Map<String, String> __ctx)
    {
        updateAnalyseTable(theme, action, __ctx, true);
    }

    @SuppressWarnings("unchecked")
    private void
    updateAnalyseTable(String theme, String action, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __delBase = __getDelegate(false);
                _DQSPServerDel __del = (_DQSPServerDel)__delBase;
                __del.updateAnalyseTable(theme, action, __ctx);
                return;
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __handleExceptionWrapper(__delBase, __ex, null);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    public static DQSPServerPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        DQSPServerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (DQSPServerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::DQSPServer"))
                {
                    DQSPServerPrxHelper __h = new DQSPServerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static DQSPServerPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        DQSPServerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (DQSPServerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::DQSPServer", __ctx))
                {
                    DQSPServerPrxHelper __h = new DQSPServerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static DQSPServerPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        DQSPServerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::DQSPServer"))
                {
                    DQSPServerPrxHelper __h = new DQSPServerPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static DQSPServerPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        DQSPServerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::DQSPServer", __ctx))
                {
                    DQSPServerPrxHelper __h = new DQSPServerPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static DQSPServerPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        DQSPServerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (DQSPServerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                DQSPServerPrxHelper __h = new DQSPServerPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static DQSPServerPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        DQSPServerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            DQSPServerPrxHelper __h = new DQSPServerPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _DQSPServerDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _DQSPServerDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, DQSPServerPrx v)
    {
        __os.writeProxy(v);
    }

    public static DQSPServerPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            DQSPServerPrxHelper result = new DQSPServerPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
