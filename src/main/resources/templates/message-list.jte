@param java.lang.String requestDateTime
@param java.util.List<io.github.shazxrin.sbemuadmin.model.Message> messages

<h2 class="font-bold text-2xl">Messages</h2>
<h5 class="text-gray-500 mb-4">Updated on ${requestDateTime}</h5>

@if(messages.isEmpty())
    <h3>No messages found.</h3>
@else
    <table class="table-fixed w-full">
        <thead>
        <tr class="border-b-2 border-gray-300">
            <th class="w-1/4 text-left">Sequence</th>
            <th class="w-3/4 text-left">Message</th>
        </tr>
        </thead>
        <tbody>
        @for(var message : messages)
            <tr class="border-b-2 border-gray-300">
                <td class="w-1/4 text-left">
                    ${message.sequenceNumber()}
                </td>
                <td class="w-3/4 text-left">
                    ${message.body()}
                </td>
            </tr>
        @endfor
        </tbody>
    </table>
@endif

