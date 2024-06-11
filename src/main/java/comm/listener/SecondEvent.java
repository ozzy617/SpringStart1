package comm.listener;

import org.springframework.context.ApplicationEvent;
public class SecondEvent extends ApplicationEvent {
        private AccessType accessType;
        public SecondEvent(Object source, AccessType accessType) {
            super(source);
            this.accessType = accessType;
        }
        public AccessType getAccessType() {
            return accessType;
        }
}
