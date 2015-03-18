SELECT EV.EVIDENCE_TYPE EV_TYPE, EV.MODALITY EV_MODALITY, EV.EVIDENCE_LINKOUT EV_LINKOUT, REL.DRUG EV_DRUG, EV.STATISTIC_VALUE EV_STAT_VAL, EV.STATISTIC_TYPE EV_STAT_TYPE
FROM DRUG_HOI_RELATIONSHIP AS REL INNER JOIN DRUG_HOI_EVIDENCE AS EV 
ON REL.ID = EV.DRUG_HOI_RELATIONSHIP 
WHERE REL.HOI = @id
