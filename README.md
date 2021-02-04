# Serverless Calendar User Agent (CUA)

The purpose of this function is to provide support for common calendar operations as defined in RFC5546.

## Introduction

There are many use-cases that require iCalendar support for managing workflows and business processes. 

For example, you may want to publish event updates to a list of email subscribers. 

Or perhaps you want to delegate meeting requests to a software defined workflow. 

Maybe you even want to manage booking of resources such as meeting rooms. 

These can all be achieved via iCalendar methods.

This function focuses specifically on calendar tranformations that conform with iCalendar method specification.
The primary interface is an SNS topic whereby calendars are published for consumption via the CUA or some other
function. In this way much of the complexity of iCalendar is removed from the business logic that can focus
simply on calendar creation and response handling.

## Overview

The function supports subscription to an SNS topic that provides calendar information and operation metadata
as follows:

    {
        "method": "<method>",
        "calendar": "<content>"
    }

## Methods

The following methods are supported by this function:

| Method         | Description                                           | Output                                                 | References |
|----------------|-------------------------------------------------------|--------------------------------------------------------|------------|
| PUBLISH        | Publish calendar information without confirmation     | Sends a published calendar and recipients to SNS topic |            |
| REQUEST        | Request acceptance of a calendar invitation           | Sends a calendar request and recipients to SNS topic   |            |
| REPLY          | Respond to a calendar invitation                      | Sends a response for a calendar request to SNS topic   |            |
| ADD            | Add to an existing calendar invitation                | Sends an updated calendar invitation to SNS topic      |            |
| CANCEL         | Cancel an existing calendar invitation                | Sends a calendar invite cancellation to SNS topic      |            |
| REFRESH        | Request the latest calendar invitation from organizer | Sends a request for the latest invitation to SNS topic |            |
| COUNTER        | Request a change to a calendar invitation             | Sends a request for change of invite to SNS topic      |            |
| DECLINECOUNTER | Reject a change to a calendar invitation              | Sends a rejection of invite changes to SNS topic       |            |
