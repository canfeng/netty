/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005-2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.netty.channel.local;

import static org.jboss.netty.channel.Channels.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jboss.netty.channel.AbstractServerChannel;
import org.jboss.netty.channel.ChannelConfig;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelSink;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 * @author Trustin Lee (tlee@redhat.com)
 */
final class LocalServerChannel extends AbstractServerChannel {
    final ChannelConfig channelConfig;

    volatile LocalAddress localAddress;
    final AtomicBoolean bound = new AtomicBoolean();

    protected LocalServerChannel(ChannelFactory factory, ChannelPipeline pipeline, ChannelSink sink) {
        super(factory, pipeline, sink);
        channelConfig = new LocalChannelConfig();
        fireChannelOpen(this);
    }

    public ChannelConfig getConfig() {
        return channelConfig;
    }

    public boolean isBound() {
        return isOpen() && bound.get();
    }

    public boolean isConnected() {
        return false;
    }

    public LocalAddress getLocalAddress() {
        return isBound()? localAddress : null;
    }

    public LocalAddress getRemoteAddress() {
        return null;
    }

    @Override
    protected boolean setClosed() {
        return super.setClosed();
    }
}
