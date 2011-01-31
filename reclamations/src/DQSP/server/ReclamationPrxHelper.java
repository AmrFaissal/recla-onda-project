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

public final class ReclamationPrxHelper extends Ice.ObjectPrxHelperBase implements ReclamationPrx
{
    public static ReclamationPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        ReclamationPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ReclamationPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::Reclamation"))
                {
                    ReclamationPrxHelper __h = new ReclamationPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ReclamationPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        ReclamationPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ReclamationPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::Reclamation", __ctx))
                {
                    ReclamationPrxHelper __h = new ReclamationPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ReclamationPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ReclamationPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::Reclamation"))
                {
                    ReclamationPrxHelper __h = new ReclamationPrxHelper();
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

    public static ReclamationPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        ReclamationPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::Reclamation", __ctx))
                {
                    ReclamationPrxHelper __h = new ReclamationPrxHelper();
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

    public static ReclamationPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        ReclamationPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (ReclamationPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                ReclamationPrxHelper __h = new ReclamationPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static ReclamationPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ReclamationPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            ReclamationPrxHelper __h = new ReclamationPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _ReclamationDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _ReclamationDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, ReclamationPrx v)
    {
        __os.writeProxy(v);
    }

    public static ReclamationPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            ReclamationPrxHelper result = new ReclamationPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
