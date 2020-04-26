
### Steps

Using `run_v3.sh` (really just instance 3) and then from [1]. Note the
official instructions have a lot of Node vs Cell but I don't see any 
Cell stuff from the Docker instance.

* A - Include Subject Alternative Name in the self-signed certificate
    - straight-forward instructions
* B - Create Self-Signed Certificates
* B.2
    - this is approximately the same as step 2
    - Security -> SSL certificate and key management > Key stores and certificates 
    - drop-down: `Root certificates keystore`
    - click on `NodeDefaultRootStore` -> `Personal certificates`
    - create drop-down: `self-signed certificate` 
        - organization is IBM
    - Apply -> Save
* B.3
    - exchange signer certs part I
    - select Security>SSL certificate and key management>Key stores and certificates        - Keystore usages menu: select All   
    - click `NodeDefaultRootStore` and `NodeDefaultSignersStore`
    - click `ExchangeSigners`
    - add new cert from `NDRS` to `NDSS`
    - Apply -> Save
* B.4
    - exchange certs part II 
    - 4. Exchange signers with the cell's default truststore
    - select Security>SSL certificate and key management>Key stores and certificates        - Keystore usages menu: select All   
    - click `NodeDefaultRootStore` and `NodeDefaultTrustStore`
    - click `ExchangeSigners`
    - add new cert from `NDRS` to `NDTS`
    - Apply -> Save
* B.5
    - Create default chained certificate in the default keystore 
    - Security>SSL certificate and key management.
    - click `NodeDefaultKeyStore`
    - click `Personal certificates`
    - Create -> Chained certificate
        - the root should be the one from the one created above
        - organization is IBM
    - Apply -> Save
* B.6
    - Create default chained certificate in default keystore for the node
    - skip this: in formal 5 is cell, and 6 is node
* B.7
    - Select Security>SSL certificate and key management.  
    - Select `Manage endpoint security configurations`
    - click on Inbound -> ... -> DefaultNode 01
        - for certificate alias, choose the 2nd one created (default)
    - click on Outbound -> ... -> DefaultNode 01
        - for certificate alias, choose the 2nd one created (default)

### Resources

* https://www.ibm.com/support/pages/how-fix-invalid-certificate-error-chrome-browser-openpages-docker-images
* https://stackoverflow.com/questions/7580508/getting-chrome-to-accept-self-signed-localhost-certificate
