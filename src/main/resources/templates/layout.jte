@import gg.jte.Content

@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Bus Admin</title>
    <script src="https://unpkg.com/htmx.org@2.0.4"></script>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
</head>
<body>
<main class="flex flex-row bg-gray-100 font-mono">
    <div class="p-8 w-1/3 h-screen bg-gray-300">
        <h1 class="text-4xl font-bold mb-4">Service Bus Admin</h1>
        <h2 class="text-2xl font-bold mb-4">Entities</h2>
        <div hx-get="/entity-list" hx-swap="innerHTML" hx-trigger="load, every 10s">
            No entities loaded yet.
        </div>
    </div>
    <div class="w-2/3 h-screen p-8">
        ${content}
    </div>
</main>
</body>
</html>
