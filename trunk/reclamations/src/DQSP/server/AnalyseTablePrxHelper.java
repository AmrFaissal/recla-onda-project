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

public final class AnalyseTablePrxHelper extends Ice.ObjectPrxHelperBase implements AnalyseTablePrx
{
    public static AnalyseTablePrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        AnalyseTablePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (AnalyseTablePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::AnalyseTable"))
                {
                    AnalyseTablePrxHelper __h = new AnalyseTablePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static AnalyseTablePrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        AnalyseTablePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (AnalyseTablePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::AnalyseTable", __ctx))
                {
                    AnalyseTablePrxHelper __h = new AnalyseTablePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static AnalyseTablePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        AnalyseTablePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::AnalyseTable"))
                {
                    AnalyseTablePrxHelper __h = new AnalyseTablePrxHelper();
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

    public static AnalyseTablePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        AnalyseTablePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::AnalyseTable", __ctx))
                {
                    AnalyseTablePrxHelper __h = new AnalyseTablePrxHelper();
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

    public static AnalyseTablePrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        AnalyseTablePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (AnalyseTablePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                AnalyseTablePrxHelper __h = new AnalyseTablePrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static AnalyseTablePrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        AnalyseTablePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            AnalyseTablePrxHelper __h = new AnalyseTablePrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _AnalyseTableDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _AnalyseTableDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, AnalyseTablePrx v)
    {
        __os.writeProxy(v);
    }

    public static AnalyseTablePrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            AnalyseTablePrxHelper result = new AnalyseTablePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
