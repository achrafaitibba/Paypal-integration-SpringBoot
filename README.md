## Why this repo
- ***Two main reasons***
1. Because I want to hh
2. Just kidding hh

I'v been struggling to integrate paypal payment gateway in my apps, since it's the only option I have no (stripe/lemonSqueezy/paddle/fastSpring....),
took me about 2 weeks reading docs and implementing it for my $0 MRR SaaS hh [telegdash.com](telegdash.com).

Some of my friends also needed to use it, and why not sharing it publicly with my network hh,
Anyways, my code sucks I know that, if you think you can fix it, send your PR.
---

[Official Paypal Api Documentation](https://developer.paypal.com/api/rest/)

---
> ⚠️ **Warning:** This repo only covers subscriptions management (create/update/revise/cancel...)

At least for now hh

One time and credits based payments are easier, do it yourself hh

---
## PayPal Environment Configuration

### Sandbox vs Live Mode
**Sandbox Environment** (Testing):
- Use for development/testing with mock transactions
- API endpoint: `https://api.sandbox.paypal.com`
- Requires sandbox credentials (client_id/client_secret from Developer Dashboard)
- Create accounts at: [PayPal Sandbox Users](https://developer.paypal.com/dashboard/accounts)

**Live Environment** (Production):
- Use for real transactions
- API endpoint: `https://api.paypal.com`
- Everything else is similar to sandbox mode

Switch environments by:
1. Changing API base URL in app config [application.yml](src/main/resources/application.yml)
2. Using corresponding environment credentials

---

## Account Setup & Credentials

### Prerequisites
1. **PayPal Business Account** (Required):
    - Register at [PayPal Business Signup](https://www.paypal.com/business)
    - Business verification required for live mode

2. **Developer Account**:
    - Access via [PayPal Developer Dashboard](https://developer.paypal.com)
    - Same credentials as business account

### Getting API Credentials:
1. Log in to [Developer Dashboard](https://developer.paypal.com)
2. Navigate to **Dashboard > My Apps & Credentials**
3. For sandbox:
    - Create sandbox app under "Sandbox" tab
    - Generate client_id/client_secret pair
4. For live:
    - Create live app under "Live" tab
    - Generate client_id/client_secret pair

Store credentials securely:
- Environment variables
- Secret management system
- Never commit to your github account, please hh

---
## Key Features
- Product creation
- Subscription plan management
- Subscription lifecycle operations
- Integration with PayPal APIs

## Core Workflow

1. **Product Creation**  
   `POST /products` ➔ Create base product  
   ```java
   Product createProduct(String productName)
   ```

2. **Plan Setup**  
   `POST /plans` ➔ Create subscription plan(s)
   ```java
   PaypalPlan createPlan(PlanRequest request)
   ```

3. **Subscription Initiation**  
   `POST /subscriptions` ➔ Create customer subscription
   ```java
   Subscription createSubscription(CreateSubscriptionRequest request)
   ```

4. **Subscription Management**
    - View details: `GET /subscriptions/{id}`
    - Modify: `PUT /subscriptions/{id}/revise`
    - Cancel: `DELETE /subscriptions/{id}`

## API Endpoints

| Method | Path | Description | Docs Reference |
|--------|------|-------------|----------------|
| POST | /products | Create new product | [PayPal Products API](https://developer.paypal.com/docs/api/catalog-products/v1/#products_create) |
| POST | /plans | Create subscription plan | [PayPal Plans API](https://developer.paypal.com/docs/api/subscriptions/v1/#plans_create) |
| POST | /subscriptions | Create new subscription | [PayPal Subscriptions API](https://developer.paypal.com/docs/api/subscriptions/v1/#subscriptions_create) |
| GET | /subscriptions/{id} | Get subscription details | [Subscription Details](https://developer.paypal.com/docs/api/subscriptions/v1/#subscriptions_get) |
| PUT | /subscriptions/revise | Modify subscription plan | Subscription Revision |
| DELETE | /subscriptions/{id} | Cancel subscription | Subscription Cancellation |


## Exception Handling
All operations throw `Exception` - recommend implementing:
- Custom exceptions
- Global error handler
- PayPal API error mapping

## Reference Links
- [PayPal Products API](https://developer.paypal.com/docs/api/catalog-products/v1/)
- [PayPal Plans API](https://developer.paypal.com/docs/api/subscriptions/v1/#plans_create)
- [PayPal Subscriptions API](https://developer.paypal.com/docs/api/subscriptions/v1/)

## Links
[LinkedIn](https://www.linkedin.com/in/achrafaitibba)

[My Website](https://www.achrafaitibba.com)

[Twitter](https://www.twitter.com/achrafaitibba)
