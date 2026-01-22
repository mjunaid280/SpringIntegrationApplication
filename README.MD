# OAuth2 Multi-Provider Integration (Backend)

## Introduction

This project is a simple backend application that demonstrates how to integrate OAuth2
using the Authorization Code flow.

The goal of this project is not just to make OAuth work, but to design it in a way that
allows adding new OAuth providers easily in the future without rewriting core logic.

The project is built using Java and Spring Boot.

---

## What This Project Does

- Implements OAuth2 Authorization Code flow
- Handles redirect and callback
- Exchanges authorization code for tokens
- Stores access and refresh tokens
- Automatically refreshes tokens when expired
- Uses access token to call a secured API
- Supports adding multiple OAuth providers
- Keeps OAuth logic separate from business logic

There is no UI. The flow is tested using browser redirects and backend APIs.

---

## OAuth Provider Used

For this implementation, **Google OAuth2** is used.

However, the design allows adding other providers like:
- GitHub
- OAuth.com demo server
- Salesforce
- Any custom OAuth2 server

---

## High-Level Design

The application is divided into a few simple components:

- **OAuthProvider**
    - Holds provider-specific details like endpoints

- **OAuthProviderRegistry**
    - Keeps track of all configured OAuth providers

- **OAuthTokenService**
    - Fetches access tokens for the logged-in user
    - Relies on Spring Security for token handling

- **TokenStore**
    - Stores tokens in Redis
    - Helps with expiry tracking and fast access

- **OAuthApiClient**
    - Calls secured APIs using the access token

This separation makes the code easier to understand and extend.

---

## OAuth Flow Explanation

1. User hits a secured endpoint
2. Spring Security redirects the user to the OAuth provider
3. User authorizes the application
4. Provider redirects back with authorization code
5. Spring Security exchanges the code for access and refresh tokens
6. Tokens are stored in Redis
7. API calls are made using the access token
8. If the token expires, Spring Security refreshes it automatically

The refresh logic is handled internally by Spring Security.

---

## Token Storage

Tokens are stored in **Redis**.

Reason for using Redis:
- Fast lookups
- Easy expiry handling using TTL
- External storage (not tied to application memory)
- Suitable for scaling the application

Redis stores:
- Access token
- Refresh token
- Expiry time

---

## Configuration

OAuth client details are not hardcoded.

They are provided using environment variables.

Example configuration:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            redirect-uri: "{baseUrl}/login/oauth2/code/google"
            scope:
              - openid
              - email
              - profile