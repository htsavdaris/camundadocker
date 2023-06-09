package com.example.camundadocker.model;

class ProcessDefinition {
    String Id;
    String Key;
    String Category;
    String Description;
    String Name;
    int Version;
    String Resource;
    String DeploymentId;
    String Diagram;
    boolean Suspended;
    String TenantId;
    String VersionTag;
    int HistoryTimeToLive;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

    public String getResource() {
        return Resource;
    }

    public void setResource(String resource) {
        Resource = resource;
    }

    public String getDeploymentId() {
        return DeploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        DeploymentId = deploymentId;
    }

    public String getDiagram() {
        return Diagram;
    }

    public void setDiagram(String diagram) {
        Diagram = diagram;
    }

    public boolean isSuspended() {
        return Suspended;
    }

    public void setSuspended(boolean suspended) {
        Suspended = suspended;
    }

    public String getTenantId() {
        return TenantId;
    }

    public void setTenantId(String tenantId) {
        TenantId = tenantId;
    }

    public String getVersionTag() {
        return VersionTag;
    }

    public void setVersionTag(String versionTag) {
        VersionTag = versionTag;
    }

    public int getHistoryTimeToLive() {
        return HistoryTimeToLive;
    }

    public void setHistoryTimeToLive(int historyTimeToLive) {
        HistoryTimeToLive = historyTimeToLive;
    }
}

class ProcessInstance {
    String Id;
    String DefinitionId;
    String BusinessKey;
    String CaseInstanceId;
    boolean Ended;
    boolean Suspended;
    String TenantId;
}

class rbProcessInstanceStart {
    rbProcessInstanceStartVariable[] variables;
    String businessKey;
    String caseInstanceId;
    String startInstructions;
    String skipCustomListeners;
    String skipIoMappings;
    String withVariablesInReturn;
}

class rbProcessInstanceStartVariable {
    String value;
    String type;
}

