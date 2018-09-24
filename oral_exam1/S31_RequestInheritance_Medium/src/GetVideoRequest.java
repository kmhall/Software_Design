import java.util.UUID;

public class GetVideoRequest extends GetRequest{

        private static int count = 0;
        private Video video;
        public GetVideoRequest(UUID id, Video video){
            super(id);
            this.video = video;
            count++;
        }

        public Video getVideo() {
            return video;
        }

        @Override
        public String toString() {
            return super.toString()+"\n"+getVideo().toString();
        }

        public static int count(){return count;}
}


