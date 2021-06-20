CREATE TABLE resource (
    resourceid TEXT NOT NULL,
	resource_name TEXT NOT NULL,
	resource_type TEXT NOT NULL,
	resource_tag TEXT,
	creation_timestamp TEXT NOT NULL,
	provision_user TEXT NOT NULL,
	deprovision_user TEXT,
	cloudprovider TEXT
);

INSERT INTO resource VALUES ('s3','aws','dev',datetime('now'),'user1','user2','aws');

SELECT name FROM sqlite_master;

CREATE TABLE resource_state (
	resource_name TEXT NOT NULL,
	resource_type TEXT NOT NULL,
	state TEXT NOT NULL, -- provisioned/provisioning/deprovisioned
	updated_timestamp TEXT NOT NULL,
	tenantid INT
);