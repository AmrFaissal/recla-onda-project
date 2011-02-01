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

public final class PassagerPrxHelper extends Ice.ObjectPrxHelperBase implements PassagerPrx
{
    public static PassagerPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        PassagerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (PassagerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::Passager"))
                {
                    PassagerPrxHelper __h = new PassagerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static PassagerPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        PassagerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (PassagerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::Passager", __ctx))
                {
                    PassagerPrxHelper __h = new PassagerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static PassagerPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        PassagerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::Passager"))
                {
                    PassagerPrxHelper __h = new PassagerPrxHelper();
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

    public static PassagerPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        PassagerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::Passager", __ctx))
                {
                    PassagerPrxHelper __h = new PassagerPrxHelper();
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

    public static PassagerPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        PassagerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (PassagerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                PassagerPrxHelper __h = new PassagerPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static PassagerPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        PassagerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            PassagerPrxHelper __h = new PassagerPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _PassagerDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _PassagerDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, PassagerPrx v)
    {
        __os.writeProxy(v);
    }

    public static PassagerPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            PassagerPrxHelper result = new PassagerPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
