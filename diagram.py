from diagrams import Diagram
from diagrams.aws.compute import Lambda
from diagrams.aws.integration import SNS
from diagrams.aws.network import APIGateway

with Diagram("Calendar User Agent", show=False, direction="TB"):

    cua = Lambda("calendar user agent")

    # APIGateway("calendar user agent api") >> cua

    topic = SNS("calendar topic")
    events = APIGateway("calendar events")
    tasks = APIGateway("calendar tasks")
    journal = APIGateway("calendar journal")
    availability = APIGateway("calendar availability")

    topic >> cua >> topic
    events >> cua >> events
    tasks >> cua >> tasks
    journal >> cua >> journal
    availability >> cua >> availability
