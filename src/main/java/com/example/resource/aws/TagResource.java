package com.example.resource.aws;

import com.amazonaws.services.resourcegroupstaggingapi.AWSResourceGroupsTaggingAPI;
import com.amazonaws.services.resourcegroupstaggingapi.model.TagResourcesRequest;
import com.example.client.impl.AWSGroupsTaggingAPIClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TagResource {
    private String resourceName;
    private Map<String, String> tagMap;

    public TagResource(String resourceName, Map<String, String> tagMap) {
        this.resourceName = resourceName;
        this.tagMap = tagMap;
    }

    public Boolean isResourceTagged() {
        return true;
    }

    public void tagResourceGroups() {

        AWSResourceGroupsTaggingAPI taggingAPI = new AWSGroupsTaggingAPIClient().getResourceClient();

        TagResourcesRequest tagResourcesRequest = new TagResourcesRequest();
        tagResourcesRequest.withTags(tagMap);
        tagResourcesRequest.setResourceARNList(new ArrayList<String>(Arrays.asList("arn:aws:kinesis:us-east-1:000000000000:stream/my-stream")));

        // @TODO - null issue here , FIX this by testing at AWS.
        taggingAPI.tagResources(tagResourcesRequest);

    }

    public String toString() {
        return "Resource{" +
                "resourceName='" + resourceName + '\'' +
                ", tagMap=" + tagMap +
                "} is tagged";
    }
}
