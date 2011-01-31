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

public interface _DQSPServerDel extends Ice._ObjectDel
{
    java.util.List<AnalyseTable> getAllAnalysisTable(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    java.util.List<Reclamation> getAllReclamations(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    java.util.List<AnalyseTable> getReclamationsSortedList(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    java.util.List<VPassager> getMailingList(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    java.util.List<VService> getAllServices(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void updateAnalyseTable(String theme, String action, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void updateActionsEntreprises(String theme, boolean validation, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    void addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
