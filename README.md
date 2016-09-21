#### What I am trying cook in this recipe

1. To embedded jetty with spring web application
2. To assemble everything for deployment using maven assembly plugin
3. Ensure startup and shutdown
   - make sure process is not running using mutually exclusive lock
   - make sure process acquires lock before starting up process
   - make sure process cleans up aquired locks
   - make sure locking mechanism is configured externally
