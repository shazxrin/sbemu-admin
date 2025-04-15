<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Bus Admin</title>
    <script src="https://unpkg.com/htmx.org@2.0.4"></script>
</head>
<body>
<main>
    <h1>Service Bus Admin</h1>

    <h2>Entities</h2>
    <div hx-get="/entities" hx-swap="innerHTML" hx-trigger="load, every 10s">
        No entities loaded yet.
    </div>
</main>
</body>
</html>
